import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Maze {
	
	public static void main(String[] args) {
		
		//Ask the user for the filename
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the filename: ");
		String fileName = input.nextLine();
		input.close();
		
		//Opening the file
		File file = new File(fileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("The file was not found, quitting!\n");
			return;
		}

		String size = scanner.nextLine();
		System.out.println("The size is " + size.length());
		int width = 0;
		int height = 0;
		for(int i = 0; i < size.length(); i++){
			char current = size.charAt(i);
			char space = ' ';
			if(current != space){
				width++;
			}
		}

		int total = 0;
		while(scanner.hasNext()){
			total++;
			scanner.next();
		}
		//Adding the first line
		total = total + width;

		System.out.println("Width is: "+ width);
		System.out.println("Total is: " + total);
		height = total / width;
		System.out.println("Height is: " + height);


		Coordinate srt_pos, end_pos;
		String maze[][];

		 try {
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("The file was not found, quitting!\n");
			return;
		}

		int startX = 0;
		int startY = 0;
		int endX = 0;
		int endY = 0;
		maze = new String[width][height];
		for(int y=0; y<height; y++)
			for(int x=0; x<width; x++){
				maze[x][y] = scanner.next();
				if(maze[x][y].equals("S")){
					System.out.println("Found Start!");
					startX = x;
					startY = y;
					System.out.println("X location is:" + startX);
					System.out.println("Y location is:" + startY);
				}
				else if(maze[x][y].equals("F")){
					System.out.println("Found end!");
					endX = x;
					endY = y;
					System.out.println("X location is:" + endX);
					System.out.println("Y location is:" + endY);
				}
			}
			srt_pos = new Coordinate(startY, startX);
			end_pos = new Coordinate(endY, endX);
		scanner.close();
		
		MazeSolver ms = new MazeSolver(maze, srt_pos, end_pos);
		
		//Find out if maze can be solved, prints maze
		boolean solved = ms.solver();
		ms.print_result();
		
		if(solved == true)
			System.out.println("Solved!");
		else
			System.out.println("Uh oh! Maze could not be solved!");
	}
}
