import java.util.ArrayList;

public class GraphBuilder {

    private Graph graph = new Graph();

    public Graph buildGraph(Maze maze) {
        Node start = new Node(maze.getStartCord().getRow(), maze.getStartCord().getCol());
        graph.addNode(start);

        return graph;
    }

    public void checkTraversable(Node n, Maze maze) {
            
        if(n == null){
            return;
        }
        // Check down
        if (n.getRow()+1 < maze.getMaze().get(n.getRow()).size() && isTraversable(maze.getMaze().get(n.getRow() + 1).get(n.getCol()))) {
            
            Node down = new Node(n.getRow() + 1, n.getCol());
            System.out.println("Down: " + down);
            graph.addNode(down);
            graph.addEdge(n, down);
            checkTraversable(down, maze);
        }

        // Check right 
        if (n.getCol()+1 < maze.getMaze().size() && isTraversable(maze.getMaze().get(n.getRow()).get(n.getCol() + 1))) {
            
            Node right = new Node(n.getRow(), n.getCol() + 1);
            System.out.println("Right: " + right);
            graph.addNode(right);
            graph.addEdge(n, right);
            checkTraversable(right, maze);
        }

        // Check left
        if (n.getCol()-1 >= 0 && isTraversable(maze.getMaze().get(n.getRow()).get(n.getCol() - 1))) {
            
            Node left = new Node(n.getRow(), n.getCol() - 1);
            System.out.println("Left: " + left);
            graph.addNode(left);
            graph.addEdge(n, left);
            checkTraversable(left, maze);
        }

        // Check up
        if (n.getRow()-1 >= 0 && isTraversable(maze.getMaze().get(n.getRow() - 1).get(n.getCol()))) {
            
            Node up = new Node(n.getRow() - 1, n.getCol());
            System.out.println("Up: " + up);
            graph.addNode(up);
            graph.addEdge(n, up);
            checkTraversable(up, maze);
        }
    }

    public boolean isTraversable(int x) {
        return x == Globals.traversableValue;
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

        System.out.println(testMaze);

        builder.graph.addNode(start);

        builder.graph.printGraph();

        builder.checkTraversable(start, testMaze);

    }
}
