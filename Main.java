


public class Main {
    
    public static void main(String[] args) {

        MazeBuilder builder = new MazeBuilder();
        
        Maze maze = builder.buildMaze();

        int size =maze.getMaze().size();
        System.out.println("size: "+size);

        MazeSolver solver = new MazeSolver(maze);
        solver.solver();
    }

}


