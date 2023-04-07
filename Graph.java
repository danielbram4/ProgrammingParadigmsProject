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

    /**
    This method performs a depth-first search (DFS) on a maze graph to find a path from the start node to the end node.
    DFS algorithm adapted from https://gist.github.com/Staticity/0af28ba92b4ea32fef74
    @param current the current node being visited
    @param end the end node to be reached
    @param visited a list of nodes that have been visited
    @param path a list of nodes that make up the path from start to end
    @return true if a path from start to end is found, false otherwise
    */
    public boolean DFSUtil(Node current, Node end, ArrayList<Node> visited, ArrayList<Node> path){
        visited.add(current);
        if(current == end){
            return true;
        }
        List<Node> neighbours = new ArrayList<>();
        neighbours = mazeGraph.get(current);
        for(Node n : neighbours){
            if(!visited.contains(n) && DFSUtil(n, end, visited, path)){
                path.add(n);
                return true;
            }
        
        }
        return false;
    }

    /**
    This method uses DFS to find a path from the start node to the end node in a maze graph.
    @param start the start node of the path
    @param end the end node of the path
    @return an ArrayList of nodes that make up the path from start to end, or null if no path is found
    */
    public ArrayList<Node> DFS(Node start, Node end){
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> path = new ArrayList<>();

        if(DFSUtil(start, end, visited, path)){
            return path;
        } else {
            return null;
        }
    }

    public boolean addNode(Node n) {
        if (!checkIfNodeExists(n)) {
            mazeGraph.put(n, new ArrayList<Node>());
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

    public void addEdge(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2) && !checkIfEdgeExists(n1, n2) && !isDiagonal(n1,n2) && isAdjacent(n1,n2)) {
            mazeGraph.get(n1).add(n2);
            mazeGraph.get(n2).add(n1);
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



    public ArrayList<Node> findPath(Node start, Node end) {
        ArrayList<Node> path = DFS(start, end);
        Collections.reverse(path);
        return path;
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
}