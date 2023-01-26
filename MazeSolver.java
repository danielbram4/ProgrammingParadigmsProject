 public class MazeSolver {

	private String[][] result;
	private Coordinate startCord, endCord;
	private String[][] maze;
	
	public MazeSolver(String[][] maze, Coordinate startCord, Coordinate endCord) {
		this.maze = maze;
		this.startCord = startCord;
		this.endCord = endCord;
		result = new String[maze.length][maze[0].length];
	}

	public boolean solver()
	{
		//Create walls and the start and end points.
		result[startCord.getX()][startCord.getY()] = "S";
		result[endCord.getX()][endCord.getY()] = "E";
		for(int x=0; x<maze.length; x++)
			for(int y=0; y<maze[x].length; y++)	
				if(maze[x][y].equals("0"))
					result[x][y] = "#";
			
		//Finds path
		boolean success = path_finder(maze, startCord);
		
		//Clears the result, null needs to be replaced by visited
		for(int x=0; x<maze.length; x++)
			for(int y=0; y<maze[x].length; y++)	
				if(result[x][y] == null)
					result[x][y] = " ";		
		result[startCord.getX()][startCord.getY()] = "S";
		result[endCord.getX()][endCord.getY()] = "E";
		
		return success;
	}
	
	public void print_result()
	{
		for(int x=0; x<result.length; x++) {
			for(int y=0; y<result[x].length; y++) {
				System.out.print(result[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
/*
 * returns true if path to exit is found, false otherwise.
*/
	private boolean path_finder(String[][] maze, Coordinate cord)
	{
		return false;
	}
}