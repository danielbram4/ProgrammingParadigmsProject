import java.util.ArrayList;

public class MazeSolver {

    public static void main(String[] args) {

        // Create instances of the MazeBuilder and GraphBuilder classes
        MazeBuilder mazeBuilder = new MazeBuilder();
        GraphBuilder graphBuilder = new GraphBuilder();
        Globals.messageManager.printWelome();
        // Use the MazeBuilder instance to build a maze

        if (args.length > 0) {
            Maze maze = mazeBuilder.buildMaze(args[0]);
            // Use the GraphBuilder instance to build a graph based on the maze
            if (maze != null) {
                Graph graph = graphBuilder.buildGraph(maze);
                if (graph != null) {
                    // Create an instance of the MazeSolver class, passing the maze as a parameter
                    SymbolConverter converter = new SymbolConverter(maze);
                    converter.printUnsolvedMaze();
                    // Find the path from the start node to the end node in the graph
                    ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

                    // Use the MazeSolver instance to build and print the solved maze
                    converter.printSolvedMaze(path);

                }
            }
        } else {
            Globals.messageManager.noFileGiven();
        }

    }
}
