import java.util.ArrayList;

public class GraphBuilder {

    private Graph graph = new Graph();

    public Graph buildGraph(Maze maze) {
        Node start = new Node(maze.getStartCord().getX(), maze.getStartCord().getY(), false);
        graph.addNode(start);

        return graph;
    }

    public Node checkTraversable(Node n, Maze maze) {

        

        if (n == null){
            return null;
        }
            
        // Check down
        if (n.getCol()+1 < maze.getMaze().get(n.getRow()).size() && isTraversable(maze.getMaze().get(n.getRow()).get(n.getCol() + 1))) {
            
            Node newNode = new Node();
            newNode.setRow(n.getRow());
            newNode.setCol(n.getCol() + 1);
            newNode.setExit(false);

            System.out.println("NEW NODE: " + newNode);
            graph.addNode(newNode);

            graph.addEdge(n, newNode);
            // return newNode;
        }

        // Check right 
        if (n.getRow()+1 < maze.getMaze().size() && isTraversable(maze.getMaze().get(n.getRow() + 1).get(n.getCol()))) {
            
            Node newNode = new Node();
            newNode.setRow(n.getRow() + 1);
            newNode.setCol(n.getCol());
            newNode.setExit(false);

            System.out.println("NEW NODE: " + newNode);
            graph.addNode(newNode);

            graph.addEdge(n, newNode);
            // return newNode;
        }

        // Check left
        if (n.getCol()-1 >= 0 && isTraversable(maze.getMaze().get(n.getRow()).get(n.getCol() - 1))) {
            
            Node newNode = new Node();
            newNode.setRow(n.getRow());
            newNode.setCol(n.getCol() - 1);
            newNode.setExit(false);

            System.out.println("NEW NODE: " + newNode);
            graph.addNode(newNode);

            graph.addEdge(n, newNode);
            // return newNode;
        }

        // Check up
        if (n.getRow()-1 >= 0 && isTraversable(maze.getMaze().get(n.getRow() - 1).get(n.getCol()))) {
            
            Node newNode = new Node();
            newNode.setRow(n.getRow() - 1);
            newNode.setCol(n.getCol());
            newNode.setExit(false);

            System.out.println("NEW NODE: " + newNode);
            graph.addNode(newNode);

            graph.addEdge(n, newNode);
            // return newNode;
        }

        return null;

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

        Maze testMaze = new Maze(maze, new Coordinate(0, 0), new Coordinate(1, 1));
        Node start = new Node(testMaze.getStartCord().getX(), testMaze.getStartCord().getY(), false);

        System.out.println(testMaze);

        builder.graph.addNode(start);

        Node n = builder.checkTraversable(start, testMaze);

        builder.graph.printGraph();


        System.out.println("NODE: " + n);
    }
}
