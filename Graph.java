import java.util.*;

public class Graph {
        
        private Map<Node, List<Node>> mazeGraph;

        public Graph() {
            this.mazeGraph = new HashMap<>();
        }

        public Graph getGraph(){
            return this;
        }

        public void addNode(Node n) {
            mazeGraph.putIfAbsent(n, new ArrayList<Node>());
            System.out.println("Node added: " + n);
        }

        public void addEdge(Node n1, Node n2) {
            mazeGraph.get(n1).add(n2);
            mazeGraph.get(n2).add(n1);
        }

        Graph createTestGraph(){
            Graph test = new Graph();
            Node n1 = new Node(0, 0);
            Node n2 = new Node(0, 1);
            test.addNode(n1);
            test.addNode(n2);
            test.addEdge(n1,n2);
            return test;
        }

        public void printGraph() {
            for (Node n : mazeGraph.keySet()) {
                System.out.println(n + " : " + mazeGraph.get(n));
            }
        }

        public List<Node> djikstra(Node start, Node end) {
            Map<Node, Integer> distance = new HashMap<>();
            Map<Node, Node> previous = new HashMap<>();
            Set<Node> visited = new HashSet<>();
            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

            for (Node n : mazeGraph.keySet()) {
                if (n == start) {
                    distance.put(n, 0);
                    queue.add(n);
                } else {
                    distance.put(n, Integer.MAX_VALUE);
                    queue.add(n);
                }
                previous.put(n, null);
            }

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                visited.add(current);

                for (Node neighbor : mazeGraph.get(current)) {
                    if (!visited.contains(neighbor)) {
                        int newDistance = distance.get(current) + 1;
                        if (newDistance < distance.get(neighbor)) {
                            distance.put(neighbor, newDistance);
                            previous.put(neighbor, current);
                            queue.add(neighbor);
                        }
                    }
                }
            }

            List<Node> path = new ArrayList<>();
            Node current = end;
            while (current != null) {
                path.add(current);
                current = previous.get(current);
            }
            Collections.reverse(path);
            return path;
        }

        public static void main(String[] args) {
            Graph test = new Graph();

            Node n1 = new Node(0, 1);
            Node n2 = new Node(0, 2);
            Node n3 = new Node(0, 3);
            Node n4 = new Node(0, 4);
            Node n5 = new Node(0, 5);
            Node n6 = new Node(0, 6);
            test.addNode(n1);
            test.addNode(n2);
            test.addNode(n3);
            test.addNode(n4);
            test.addNode(n5);
            test.addNode(n6);
            test.addEdge(n1,n2);
            test.addEdge(n1,n3);
            test.addEdge(n2,n4);
            test.addEdge(n3,n4);
            test.addEdge(n4,n5);
            test.addEdge(n1,n6);
            test.addEdge(n6,n5);
            
            test.printGraph();


            List<Node> path = test.djikstra(n1, n5);

            System.out.println("Path: " + path);
        }



}