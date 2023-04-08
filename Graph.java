import java.util.*;

public class Graph {

    // Map to store the graph representation of the maze
    private Map<Node, List<Node>> mazeGraph = new HashMap<>();
    private Node startNode, endNode;

    // Constructor for Graph
    public Graph() {
        this.mazeGraph = new HashMap<>();
    }

    /**
     * Method to get the Graph object.
     * 
     * @return The graph object.
     */
    public Graph getGraph() {
        return this;
    }

    /**
     * Method to get the mazeGraph.
     * 
     * @return The maze graph.
     */
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

    /**
     * Method to check if a node exists in the mazeGraph.
     * 
     * @param n The node to check.
     * @return `true` if the node exists, `false` otherwise.
     */
    public boolean checkIfNodeExists(Node n) {
        for (Node node : mazeGraph.keySet()) {
            if (node.getRow() == n.getRow() && node.getCol() == n.getCol()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an edge to the graph.
     * 
     * @param n1 First node to connect.
     * @param n2 Second node to connect.
     */
    public void addEdge(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2) && !checkIfEdgeExists(n1, n2) && !isDiagonal(n1,n2) && isAdjacent(n1,n2)) {
            mazeGraph.get(n1).add(n2);
            mazeGraph.get(n2).add(n1);
        }
    }

    /**
     * Checks if two nodes are diagonally connected.
     * 
     * @param n1 First node to check.
     * @param n2 Second node to check.
     * @return True if the nodes are diagonally connected, false otherwise.
     */
    public boolean isDiagonal(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2)) {
            if(n1.getRow() != n2.getRow() && n1.getCol() != n2.getCol()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two nodes are adjacent.
     * 
     * @param n1 First node to check.
     * @param n2 Second node to check.
     * @return True if the nodes are adjacent, false otherwise.
     */
    public boolean isAdjacent(Node n1, Node n2) {
        if (checkIfNodeExists(n1) && checkIfNodeExists(n2)) {
            // Returns true if the two nodes are in the same row and the column difference
            // is 1.
            if(n1.getRow() == n2.getRow() && (n1.getCol() - n2.getCol() == 1 || n1.getCol() - n2.getCol() == -1)) {
                return true;
            }
            // Otherway around
            if(n1.getCol() == n2.getCol() && (n1.getRow() - n2.getRow() == 1 || n1.getRow() - n2.getRow() == -1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an edge exists between two nodes.
     * 
     * @param n1 First node to check.
     * @param n2 Second node to check.
     * @return True if the edge exists, false otherwise.
     */
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

    /**
     * Checks if the given node is in the graph.
     * 
     * @param n The node to check for.
     * @return True if the node is in the graph, false otherwise.
     */
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

    /**
     * Sets the end node of the graph.
     * 
     * @param n The node to set as the end node.
     */
    public void setEndNode(Node n) {
        endNode = n;
    }

    /**
     * Gets the start node of the graph.
     * 
     * @return The start node.
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * Gets the end node of the graph.
     * 
     * @return The end node.
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * Retrieves a node from the graph with the given row and column values.
     * 
     * @param r The row value of the node.
     * @param c The column value of the node.
     * @return The node with the specified row and column values, or `null` if no
     *         such node exists in the graph.
     */
    public Node getNode(int r, int c) {
        for (Node n : mazeGraph.keySet()) {
            if (n.getRow() == r && n.getCol() == c) {
                return n;
            }
        }
        return null;
    }

    /**
     * Prints the graph of the maze.
     * The graph consists of a representation of all the nodes and their neighbours.
     * Additionally, the start and end nodes of the maze are also printed.
     */
    public void printGraph() {
        for (Node n : mazeGraph.keySet()) {
            System.out.println(n + " : " + mazeGraph.get(n));
        }

        System.out.println("Start Node: " + startNode);
        System.out.println("End Node: " + endNode);
    }
}