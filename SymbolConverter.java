import java.util.ArrayList;

/*
 * Symbol Converter Takes the unsolved maze and converts the numbers to characters
 * All of these operations are for print purposes only
 */
public class SymbolConverter {

	
	private ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();	//2D arrayList that stores the string representation of the maze
	private Coordinate startCord, endCord;
	Messages message = new Messages();

	public SymbolConverter() {
		result = null;
	}


	public SymbolConverter(Maze maze){													//Makes a copy of the maze
		for(int x=0; x<maze.getMaze().size(); x++) {									
			ArrayList<String> resultCols = new ArrayList<String>();
			for(int y=0; y<maze.getMaze().get(x).size(); y++) {
				resultCols.add(maze.getMaze().get(x).get(y).toString());				//get the string representation of each element in the maze and add it to the resultCols arrayList
			}
			result.add(resultCols);
		}
		startCord = maze.getStartCord();
		endCord = maze.getEndCord();
	}

	public boolean printUnsolvedMaze()
	{
		result.get(startCord.getRow()).set(startCord.getCol(), Globals.startSymbol);	//Create walls and the start and end points.
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

	public void printSolvedMaze(ArrayList<Node> path){									
		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {
				
				if(result.get(x).get(y).equals(Globals.wallValue)) {					// Check if the element is a wall value.
					result.get(x).set(y, Globals.wallSymbol);							// Replace the wall value with wall symbol.
				} else if(result.get(x).get(y).equals(Globals.pathValue)){
					result.get(x).set(y, Globals.pathSymbol);							// Replace the path value with path symbol.
				}
			}
		}

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

		print_result();
	}
	
	public void print_result()
	{
		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {
				System.out.print(result.get(x).get(y) + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}