import java.util.ArrayList;


public class GraphBuilder {

    private Graph graph = new Graph();

    /**
     * Builds a graph from the given maze.
     * 
     * @param maze The maze to build the graph from.
     * @return The built graph.
     */
    public Graph buildGraph(Maze maze) {
        Node start = new Node(maze.getStartCord().getRow(), maze.getStartCord().getCol());
        graph.addNode(start);
        graph.setStartNode(start);
        checkTrav(start, maze);
        graph.setEndNode(graph.getNode(maze.getEndCord().getRow(), maze.getEndCord().getCol()));
        return graph;
    }

    /**
     * Checks if the node one row up from the given node is traversable,
     * adds the node to the graph, and adds an edge between the nodes.
     * 
     * @param n The node to check the row above.
     * @param maze The maze that contains the nodes.
     * @return The node that was checked.
     */
    public Node checkUp(Node n, Maze maze) {
        if (isInRowBounds(n.getRow() - 1, maze) && isTraversable(getUpValue(n, maze))) {

            Node up = new Node(n.getRow() - 1, n.getCol());
            if (graph.addNode(up)) {
                graph.addEdge(n, up);
                return checkTrav(up, maze);
            }
        } 
        return null;
    }

    /**
     * Checks if the node one row down from the given node is traversable,
     * adds the node to the graph, and adds an edge between the nodes.
     * 
     * @param n The node to check the row below.
     * @param maze The maze that contains the nodes.
     * @return The node that was checked.
     */
    public Node checkDown(Node n, Maze maze) {
        if (isInRowBounds(n.getRow() + 1, maze) && isTraversable(getDownValue(n, maze))) {

            Node down = new Node(n.getRow() + 1, n.getCol());
            if (graph.addNode(down)) {
                graph.addEdge(n, down);
                return checkTrav(down, maze);
            }
        }
        return null;
    }

    /**
     * Checks if the node one column left from the given node is traversable,
     * adds the node to the graph, and adds an edge between the nodes.
     * 
     * @param n The node to check the row below.
     * @param maze The maze that contains the nodes.
     * @return The node that was checked.
     */
    public Node checkLeft(Node n, Maze maze) {
        if (isInColBounds(n.getCol() - 1, maze) && isTraversable(getLeftValue(n, maze))) {

            Node left = new Node(n.getRow(), n.getCol() - 1);
            if (graph.addNode(left)) {
                graph.addEdge(n, left);
                return checkTrav(left, maze);
            }
        }
        return null;
    }

    /**
     * Checks if the node one column right from the given node is traversable,
     * adds the node to the graph, and adds an edge between the nodes.
     * 
     * @param n The node to check the row below.
     * @param maze The maze that contains the nodes.
     * @return The node that was checked.
     */
    public Node checkRight(Node n, Maze maze) {
        if (isInColBounds(n.getCol() + 1, maze) && isTraversable(getRightValue(n, maze))) {

            Node right = new Node(n.getRow(), n.getCol() + 1);
            if (graph.addNode(right)) {
                graph.addEdge(n, right);
                return checkTrav(right, maze);
            }
        }
        return null;
    }

    /**
     * This method is used to check the traversability of the given node and its neighbors in the given maze.
     * It calls the checkUp, checkDown, checkLeft, and checkRight methods to check the neighbors,
     * adds the neighbors to the graph if they are traversable, and adds edges between the nodes.
     * If a neighbor is traversable, it recursively calls itself with that node as a parameter.
     * 
     * @param n The node to check and add to the graph.
     * @param maze The maze that contains the nodes.
     * @return The node that was checked.
     */
    public Node checkTrav(Node n, Maze maze){

        Node up = checkUp(n, maze);
        Node down = checkDown(n, maze);
        Node left = checkLeft(n, maze);
        Node right = checkRight(n, maze);

        if(up != null){
            graph.addEdge(n, up);
            return checkTrav(up, maze);
        }
        if(down != null){
            graph.addEdge(n, down);
            return checkTrav(down, maze);
        }
        if(left != null){
            graph.addEdge(n, left);
            return checkTrav(left, maze);
        }
        if(right != null){
            graph.addEdge(n, right);
            return checkTrav(right, maze);
        }

        return n;
    }

    /**
     * Determines if the given integer value is equal to the traversable value 
     * of the maze.
     * 
     * @param x The integer value to check.
     * @return True if the given integer value is equal to the traversable value, 
     * false otherwise.
     */
    public boolean isTraversable(int x) {
        return x == Globals.traversableValue;
    }

    /**
     * This method is used to determine if a given row index is within the bounds of the given maze.
     * 
     * @param row The row index to check.
     * @param maze The maze that contains the nodes.
     * @return True if the row index is within the bounds of the maze, false otherwise.
     */
    public boolean isInRowBounds(int row, Maze maze) {
        return row >= 0 && row < maze.getMaze().size();
    }

    /**
     * This method is used to determine if a given column index is within the bounds of the given maze.
     * 
     * @param col The column index to check.
     * @param maze The maze that contains the nodes.
     * @return True if the column index is within the bounds of the maze, false otherwise.
     */
    public boolean isInColBounds(int col, Maze maze) {
        return col >= 0 && col < maze.getMaze().get(0).size();
    }

    /**
     * This method is used to get the value of the node one row above the given node in the given maze.
     * 
     * @param n The node to get the value above.
     * @param maze The maze that contains the nodes.
     * @return The value of the node one row above the given node.
     */
    public int getUpValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow() - 1).get(n.getCol());
    }

    /**
     * Returns the value of the node one row above the given node in the maze.
     * 
     * @param n The node to get the value of the node above.
     * @param maze The maze that contains the nodes.
     * @return The value of the node one row above the given node in the maze.
     */
    public int getDownValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow() + 1).get(n.getCol());
    }

    /**
     * Returns the value in the maze at the position one column to the right of the
     * given node.
     * 
     * @param n    The node whose left value is to be returned.
     * @param maze The maze that contains the nodes.
     * @return The value at the position one column to the left of the node in the
     *         maze.
     */
    public int getRightValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow()).get(n.getCol() + 1);
    }

    /**
     * Returns the value in the maze at the position one column to the left of the
     * given node.
     * 
     * @param n    The node whose left value is to be returned.
     * @param maze The maze that contains the nodes.
     * @return The value at the position one column to the left of the node in the
     *         maze.
     */
    public int getLeftValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow()).get(n.getCol() - 1);
    }



    //You can use this main method for local testing delete when done
    public static void main(String args[]) {
        GraphBuilder builder = new GraphBuilder();

        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        ArrayList<Integer> row2 = new ArrayList<Integer>();

        row1.add(1);
        row1.add(1);
        row2.add(1);
        row2.add(1);
        maze.add(row1);
        maze.add(row2);

        System.out.println(maze);

        Maze testMaze = new Maze(maze, new Coordinate(0, 0), new Coordinate(1, 1));
        Node start = new Node(testMaze.getStartCord().getRow(), testMaze.getStartCord().getCol());

        builder.graph.addNode(start);

        builder.checkTrav(start, testMaze);

        builder.graph.printGraph();

    }
}
