import java.util.*;

public class Graph {

    private Map<Node, List<Node>> mazeGraph = new HashMap<>();
    private Node startNode, endNode;

    public Graph() {
        this.mazeGraph = new HashMap<>();
    }

    public Graph getGraph() {
        return this;
    }

    public Map<Node, List<Node>> getMazeGraph() {
        return mazeGraph;
    }

    public boolean addNode(Node n) {

        if (!checkIfNodeExists(n)) {
            mazeGraph.put(n, new ArrayList<Node>());
            // System.out.println("Node added: " + n);
            return true;
        }
        return false;
    }

    public boolean checkIfNodeExists(Node n) {
        for (Node node : mazeGraph.keySet()) {
            if (node.getRow() == n.getRow() && node.getCol() == n.getCol()) {
                return true;
            }
        }
        return false;
    }


    //Right now i'm adding two edges between each node, if there are problems with
    //DFS with backtracking we may need to edit so that only one edge is being created between the nodes.
    public void addEdge(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2) && !checkIfEdgeExists(n1, n2) && !isDiagonal(n1,n2) && isAdjacent(n1,n2)) {
            mazeGraph.get(n1).add(n2);
            mazeGraph.get(n2).add(n1);
            // System.out.println("Edge added: " + n1 + " " + n2 + "");
        }
    }

    public boolean isDiagonal(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2)) {
            if(n1.getRow() != n2.getRow() && n1.getCol() != n2.getCol()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjacent(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2)) {
            if(n1.getRow() == n2.getRow() && (n1.getCol() - n2.getCol() == 1 || n1.getCol() - n2.getCol() == -1)) {
                return true;
            }
            if(n1.getCol() == n2.getCol() && (n1.getRow() - n2.getRow() == 1 || n1.getRow() - n2.getRow() == -1)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfEdgeExists(Node n1, Node n2) {

        if (checkIfNodeExists(n1) && checkIfNodeExists(n2)) {
            for (Node node : mazeGraph.get(n1)) {
                if (node.getRow() == n2.getRow() && node.getCol() == n2.getCol()) {
                    return true;
                }
            }
            for (Node node : mazeGraph.get(n2)) {
                if (node.getRow() == n1.getRow() && node.getCol() == n1.getCol()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsNode(Node n) {
        return mazeGraph.containsKey(n);
    }

    public void setStartNode(Node n) {
            startNode = n;
    }

    public void setEndNode(Node n) {
            endNode = n;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Node getNode(int r, int c) {
        for (Node n : mazeGraph.keySet()) {
            if (n.getRow() == r && n.getCol() == c) {
                return n;
            }
        }
        return null;
    }

    public void printGraph() {
        for (Node n : mazeGraph.keySet()) {
            System.out.println(n + " : " + mazeGraph.get(n));
        }

        System.out.println("Start Node: " + startNode);
        System.out.println("End Node: " + endNode);
    }


    //This is the current search algorithm. Make sure that the new search algorithm returns an ArrayList of Nodes
    //for printing to the console.
    public ArrayList<Node> djikstra2(Node start, Node end){
        start.setDistance(0);
        ArrayList<Node> queue = new ArrayList<>();

        queue.add(start);
        int x = 0;
        while(!queue.isEmpty()){
            Node current = queue.get(x);
            if(current == end){
                break;
            }

            for(Node neighbour : mazeGraph.get(current)){
                int newDistance = current.getDistance() + 1;
                if(newDistance < neighbour.getDistance()){
                    queue.remove(neighbour);
                    neighbour.setDistance(newDistance);
                    neighbour.setPrevious(current);
                    queue.add(neighbour);
                }
            }
            x++;
        }

        ArrayList<Node> path = new ArrayList<>();
        for(Node n = end; n != null; n = n.getPrevious()){
            path.add(n);
        }

        Collections.reverse(path);

        return path;
    }

    public ArrayList<Node> findPath(Node start, Node end) {
        // ArrayList<Node> path = djikstra2(start, end);
        ArrayList<Node> path = DFS(start, end);
        // System.out.println("Path: " + path);

        return path;
    }

    // DFS algorithm adapted from https://gist.github.com/Staticity/0af28ba92b4ea32fef74
    public boolean DFSScott(Node current, Node end, ArrayList<Node> visited, ArrayList<Node> path){
        visited.add(current);
        if(current == end){
            return true;
        }
        List<Node> neighbours = new ArrayList<>();
        neighbours = mazeGraph.get(current);
        for(Node n : neighbours){
            if(!visited.contains(n) && DFSScott(n, end, visited, path)){
                path.add(n);
                return true;
            }
        
        }
        return false;
    }

    public ArrayList<Node> DFS(Node start, Node end){
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();

        if(DFSScott(start, end, visited, path)){
            // System.out.println(path);
            return path;
        } else {
            return null;
        }
    }
    
    //Used for testing delete when done
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
        test.addEdge(n1, n2);
        test.addEdge(n2,n3);
        test.addEdge(n1, n3);
        test.addEdge(n2, n4);
        test.addEdge(n3, n4);
        test.addEdge(n4, n5);
        test.addEdge(n1, n6);
        test.addEdge(n6, n5);

        test.setStartNode(n1);
        test.setEndNode(n5);

        test.printGraph();

        // List<Node> path1 = test.djikstra2(n1, n5);
        System.out.println("Path: ");
        test.DFS(n1, n5);
    }

}