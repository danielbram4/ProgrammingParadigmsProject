import java.util.ArrayList;

public class SymbolConverter {

	//result is a 2D arrayList that stores the string representation of the maze
	private ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	//startCord and endCord store the start and end coordinates of the maze
	private Coordinate startCord, endCord;
	Messages message = new Messages();

	
	//constructor without parameters, sets result to null
	public SymbolConverter() {
		result = null;
	}

	//constructor with a Maze object parameter, initializes the MazeprintUnsolvedMaze class with the maze information
	public SymbolConverter(Maze maze){

		//Copy maze values to result string arrayList
		for(int x=0; x<maze.getMaze().size(); x++) {
			ArrayList<String> resultCols = new ArrayList<String>();
			for(int y=0; y<maze.getMaze().get(x).size(); y++) {
				//get the string representation of each element in the maze and add it to the resultCols arrayList
				resultCols.add(maze.getMaze().get(x).get(y).toString());
			}
			//add the row of string values to the result 2D arrayList
			result.add(resultCols);
		}

		//get the start and end coordinates from the maze object
		startCord = maze.getStartCord();
		endCord = maze.getEndCord();
	}

	public boolean printUnsolvedMaze()
	{
		//Create walls and the start and end points.
		result.get(startCord.getRow()).set(startCord.getCol(), Globals.startSymbol);
		result.get(endCord.getRow()).set(endCord.getCol(), Globals.endSymbol);

		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {

				if(result.get(x).get(y).equals(Globals.wallValue)) {
					result.get(x).set(y, Globals.wallSymbol);
				} else if(result.get(x).get(y).equals(Globals.pathValue)){
					result.get(x).set(y, Globals.pathSymbol);
				}
			}
		}

		print_result();
		
		return true;
	}

	/**
	 * printSolvedMaze is a function to build and print the final solution of the
	 * maze.
	 * 
	 * @param path The path of the solution found in the maze.
	 */
	public void printSolvedMaze(ArrayList<Node> path){

		// Loop through each element in the result list.
		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {
				// Check if the element is a wall value.
				if(result.get(x).get(y).equals(Globals.wallValue)) {
					// Replace the wall value with wall symbol.
					result.get(x).set(y, Globals.wallSymbol);
				} else if(result.get(x).get(y).equals(Globals.pathValue)){
					// Replace the path value with path symbol.
					result.get(x).set(y, Globals.pathSymbol);
				}
			}
		}

		// Loop through each element in the path list.
		try{
			for(int x=0; x<path.size(); x++){
				// Replace the elements in the result list with X's (the solution path) at the coordinates of the path.
				result.get(path.get(x).getCoordinates().getRow()).set(path.get(x).getCoordinates().getCol(), Globals.ANSI_GREEN +  "X" + Globals.TEXT_RESET);
			}
		}catch(NullPointerException e){
			message.PATHNOTFOUND();
		}

		result.get(startCord.getRow()).set(startCord.getCol(),Globals.ANSI_BLUE +  "S" + Globals.TEXT_RESET);
		result.get(endCord.getRow()).set(endCord.getCol(),Globals.ANSI_BLUE +  "E" + Globals.TEXT_RESET);

		// Call the print_result function to print the final solution of the maze.
		print_result();
	}
	
	// Method to print the final result of the maze
	public void print_result()
	{

		// Loop through each row of the `result` 2D arrayList
		for(int x=0; x<result.size(); x++) {
			// Loop through each column of the current row of the `result` 2D arrayList
			for(int y=0; y<result.get(x).size(); y++) {
				// Print the value at the current row and column of the `result` 2D arrayList
				System.out.print(result.get(x).get(y) + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}