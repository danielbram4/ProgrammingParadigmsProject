import java.util.ArrayList;
public class Main {
    
    public static void main(String[] args) {

        // Create instances of the MazeBuilder and GraphBuilder classes
        MazeBuilder mazeBuilder = new MazeBuilder();
        GraphBuilder graphBuilder = new GraphBuilder();

        // Use the MazeBuilder instance to build a maze
        Maze maze = mazeBuilder.buildMaze();

        // Use the GraphBuilder instance to build a graph based on the maze
        if(maze != null){
            Graph graph = graphBuilder.buildGraph(maze);
            if(graph != null){
        // graph.getGraph().printGraph();

        // Create an instance of the MazeSolver class, passing the maze as a parameter
        MazeSolver solver = new MazeSolver(maze);
        solver.solver();
        // Find the path from the start node to the end node in the graph
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        // Use the MazeSolver instance to build and print the solved maze
        solver.buildPrintMaze(path);
    }
    }
    }
}


