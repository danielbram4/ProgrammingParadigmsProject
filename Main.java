


public class Main {
    
    public static void main(String[] args) {

        MazeBuilder mazeBuilder = new MazeBuilder();
        GraphBuilder graphBuilder = new GraphBuilder();
        
        Maze maze = mazeBuilder.buildMaze();
        Graph graph = graphBuilder.buildGraph(maze);

        graph.getGraph().printGraph();

        MazeSolver solver = new MazeSolver(maze);
        solver.solver();
    }
}


