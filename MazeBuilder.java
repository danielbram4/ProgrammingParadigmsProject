import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazeBuilder {

    public Maze buildMaze(){

        // Ask the user for the filename
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String fileName = input.nextLine();
        input.close();
    
        // Opening the file
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found, quitting!\n");
            return null;
        }
    
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        
        int row = 0;
        
        while(scanner.hasNextLine()){
            String currentLine = scanner.nextLine();
            String[] lineFrag = currentLine.split(";");
            String[] colVals = lineFrag[0].split(" ");

            ArrayList<Integer> col = new ArrayList<Integer>();
            for(int i = 0; i < colVals.length; i++){
                if(colVals[i].equals(Globals.startValue)){
                    System.out.println("Found Start at: (" + i + ", " + row + ")");
                    startX = i;
                    startY = row;
                } else if(colVals[i].equals(Globals.exitValue)){
                    System.out.println("Found End at: (" + i + ", " + row + ")");
                    endX = i;
                    endY = row;
                    colVals[i] = Globals.pathValue;
                }

                col.add(Integer.parseInt(colVals[i]));
            }

            maze.add(row, col);

            row++;
        }
        Coordinate start_pos = new Coordinate(startY, startX);
        Coordinate end_pos = new Coordinate(endY, endX);
        scanner.close();
    
        // MazeSolver ms = new MazeSolver(maze, srt_pos, end_pos);
    
        // // Find out if maze can be solved, prints maze
        // boolean solved = ms.solver();
        // ms.print_result();
    
        // if (solved == true)
        //     System.out.println("Solved!");
        // else
        //     System.out.println("Uh oh! Maze could not be solved!");
    return new Maze(maze, start_pos, end_pos);
    }
}
