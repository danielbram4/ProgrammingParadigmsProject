import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeBuilder {

    // Builds a Maze instance from a file
    public Maze buildMaze(String fileName) {

        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            Globals.messageManager.FILENOTFOUND();
            return null;
        }

        // Initializing start and end coordinates
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        // 2D Array List
        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        int row = 0;

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String[] lineFrag = currentLine.split(";");
            String[] colVals = lineFrag[0].split(" ");

            ArrayList<Integer> col = new ArrayList<Integer>();

            // Iterate through the values in the current row
            for (int i = 0; i < colVals.length; i++) {
                if (colVals[i].equals(Globals.startValue)) {
                    startX = i;
                    startY = row;
                } else if (colVals[i].equals(Globals.exitValue)) {
                    endX = i;
                    endY = row;
                    colVals[i] = Globals.pathValue;
                }
                col.add(Integer.parseInt(colVals[i]));
            }
            maze.add(row, col);
            row++;
        }

        // Create Coordinate objects to store the start and end points of the maze
        Coordinate start_pos = new Coordinate(startY, startX);
        Coordinate end_pos = new Coordinate(endY, endX);
        scanner.close();

        return new Maze(maze, start_pos, end_pos);
    }
}
