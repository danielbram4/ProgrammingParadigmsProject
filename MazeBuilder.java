import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeBuilder {

    public String getFileName() {
        // Ask the user for the filename
        Scanner input = new Scanner(System.in);
        // System.out.print("Enter the filename: ");
        message.ENTERFILENAME();
        String fileName = input.nextLine();
        input.close();

        return fileName;
    }

    // Builds a Maze instance from a file
    public Maze buildMaze(String fileName) {

        // Opening the file
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            // System.out.println("The file was not found, quitting!\n");
            message.FILENOTFOUND();
            return null;
        }

        // Variables to store the start and end points of the maze
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        // Create an ArrayList to store the maze data
        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();

        int row = 0; // Current row number

        // Read the file line by line
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String[] lineFrag = currentLine.split(";");
            String[] colVals = lineFrag[0].split(" ");

            // Create a new ArrayList to store the values for the current row
            ArrayList<Integer> col = new ArrayList<Integer>();

            // Iterate through the values in the current row
            for (int i = 0; i < colVals.length; i++) {
                if (colVals[i].equals(Globals.startValue)) {
                    System.out.println("Found Start at: (" + row + ", " + i + ")");
                    startX = i;
                    startY = row;
                } else if (colVals[i].equals(Globals.exitValue)) {
                    System.out.println("Found End at: (" + row + ", " + i + ")");
                    endX = i;
                    endY = row;
                    colVals[i] = Globals.pathValue;
                }

                // Add the value to the current row's ArrayList
                col.add(Integer.parseInt(colVals[i]));
            }

            // Add the current row to the maze ArrayList
            maze.add(row, col);

            row++;
        }

        // Create Coordinate objects to store the start and end points of the maze
        Coordinate start_pos = new Coordinate(startY, startX);
        Coordinate end_pos = new Coordinate(endY, endX);
        scanner.close();

        // Return a new Maze instance using the maze data, start position, and end
        // position
        return new Maze(maze, start_pos, end_pos);
    }
}
