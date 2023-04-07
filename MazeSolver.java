import java.util.ArrayList;

public class MazeSolver {

    public static void main(String[] args) {
        MazeBuilder mazeBuilder = new MazeBuilder();
        GraphBuilder graphBuilder = new GraphBuilder();
        Globals.messageManager.printWelome();

        if (args.length > 0) {
            Maze maze = mazeBuilder.buildMaze(args[0]);                                                 //Build Maze from file path
            if (maze != null) { 
                Graph graph = graphBuilder.buildGraph(maze);                                            //Convert 2D array list to a graph
                if (graph != null) {
                    SymbolConverter converter = new SymbolConverter(maze);                              //Convert numbers to characters for printing
                    converter.printUnsolvedMaze();
                    ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());    //Find Solution
                    converter.printSolvedMaze(path);
                }
            }
        } else {
            Globals.messageManager.noFileGiven();
        }

    }
}
