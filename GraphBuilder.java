import java.util.ArrayList;

public class GraphBuilder {

    private Graph graph = new Graph();

    public Graph buildGraph(Maze maze) {
        Node start = new Node(maze.getStartCord().getRow(), maze.getStartCord().getCol());
        graph.addNode(start);

        return graph;
    }

    // public Node checkTraversable(Node n, Maze maze) {

    //     System.out.println("Checking: " + n + "");
    //     // Check down
    //     if (isInRowBounds(n.getRow() + 1, maze) && isTraversable(getDownValue(n, maze))) {

    //         Node down = new Node(n.getRow() + 1, n.getCol());
    //         System.out.println("Down: " + down);
    //         if (graph.addNode(down)) {
    //             graph.addEdge(n, down);
    //             return checkTraversable(down, maze);
    //         }
    //     }

    //     if (isInColBounds(n.getCol() + 1, maze) && isTraversable(getRightValue(n, maze))) {

    //         Node right = new Node(n.getRow(), n.getCol() + 1);
    //         System.out.println("Right: " + right);
    //         if (graph.addNode(right)) {
    //             graph.addEdge(n, right);
    //             return checkTraversable(right, maze);
    //         }
    //     }

    //     if (isInColBounds(n.getCol() - 1, maze) && isTraversable(getLeftValue(n, maze))) {

    //         Node left = new Node(n.getRow(), n.getCol() - 1);
    //         System.out.println("Left: " + left);
    //         if (graph.addNode(left)) {
    //             graph.addEdge(n, left);
    //             return checkTraversable(left, maze);
    //         }
    //     }

    //     if (isInRowBounds(n.getRow() - 1, maze) && isTraversable(getUpValue(n, maze))) {

    //         Node up = new Node(n.getRow() - 1, n.getCol());
    //         System.out.println("Up: " + up);
    //         if (graph.addNode(up)) {
    //             graph.addEdge(n, up);
    //             return checkTraversable(up, maze);
    //         }
    //     } 
    //     return n;
    // }

    public Node checkUp(Node n, Maze maze) {
        if (isInRowBounds(n.getRow() - 1, maze) && isTraversable(getUpValue(n, maze))) {

            Node up = new Node(n.getRow() - 1, n.getCol());
            System.out.println("Up: " + up);
            if (graph.addNode(up)) {
                graph.addEdge(n, up);
                return checkTraversable(up, maze);
            }
        } 
        return null;
    }

    public Node checkDown(Node n, Maze maze) {
        if (isInRowBounds(n.getRow() + 1, maze) && isTraversable(getDownValue(n, maze))) {

            Node down = new Node(n.getRow() + 1, n.getCol());
            System.out.println("Down: " + down);
            if (graph.addNode(down)) {
                graph.addEdge(n, down);
                return checkTraversable(down, maze);
            }
        }
        return null;
    }

    public Node checkLeft(Node n, Maze maze) {
        if (isInColBounds(n.getCol() - 1, maze) && isTraversable(getLeftValue(n, maze))) {

            Node left = new Node(n.getRow(), n.getCol() - 1);
            System.out.println("Left: " + left);
            if (graph.addNode(left)) {
                graph.addEdge(n, left);
                return checkTraversable(left, maze);
            }
        }
        return null;
    }

    public Node checkRight(Node n, Maze maze) {
        if (isInColBounds(n.getCol() + 1, maze) && isTraversable(getRightValue(n, maze))) {

            Node right = new Node(n.getRow(), n.getCol() + 1);
            System.out.println("Right: " + right);
            if (graph.addNode(right)) {
                graph.addEdge(n, right);
                return checkTraversable(right, maze);
            }
        }
        return null;
    }

    public void checkTrav(Node n, Maze maze){

        Node up = checkUp(n, maze);
        Node down = checkDown(n, maze);
        Node left = checkLeft(n, maze);
        Node right = checkRight(n, maze);

        if(up != null){
            graph.addEdge(n, up);
            checkTrav(up, maze);
        }
        if(down != null){
            graph.addEdge(n, down);
            checkTrav(down, maze);
        }
        if(left != null){
            graph.addEdge(n, left);
            checkTrav(left, maze);
        }
        if(right != null){
            graph.addEdge(n, right);
            checkTrav(right, maze);
        }
    }

    public boolean isTraversable(int x) {
        return x == Globals.traversableValue;
    }

    public boolean isInRowBounds(int row, Maze maze) {
        return row >= 0 && row < maze.getMaze().size();
    }

    public boolean isInColBounds(int col, Maze maze) {
        return col >= 0 && col < maze.getMaze().get(0).size();
    }

    public int getUpValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow() - 1).get(n.getCol());
    }

    public int getDownValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow() + 1).get(n.getCol());
    }

    public int getRightValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow()).get(n.getCol() + 1);
    }

    public int getLeftValue(Node n, Maze maze) {
        return maze.getMaze().get(n.getRow()).get(n.getCol() - 1);
    }

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
