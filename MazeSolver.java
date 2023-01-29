import java.util.ArrayList;

public class MazeSolver {

	private ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	private Coordinate startCord, endCord;

	
	
	public MazeSolver() {
		result = null;
	}

	public MazeSolver(Maze maze){

		System.out.println("THIS SIZE: " +maze.getMaze().size());
		for(int x=0; x<maze.getMaze().size(); x++) {
			ArrayList<String> resultCols = new ArrayList<String>();
			for(int y=0; y<maze.getMaze().get(x).size(); y++) {
				resultCols.add(maze.getMaze().get(x).get(y).toString());
			}
			result.add(resultCols);
		}

		startCord = maze.getStartCord();
		endCord = maze.getEndCord();

		System.out.println(startCord.getX() + " " + endCord);
	}

	public boolean solver()
	{
		//Create walls and the start and end points.
		result.get(startCord.getY()).set(startCord.getX(), Globals.startSymbol);
		result.get(endCord.getY()).set(endCord.getX(), Globals.endSymbol);

		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {

				if(result.get(x).get(y).equals(Globals.wallValue)) {
					result.get(x).set(y, Globals.wallSymbol);
				} else if(result.get(x).get(y).equals(Globals.pathValue)){
					result.get(x).set(y, Globals.pathSymbol);
				}
			}
		}
			
		//Finds path
		// boolean success = path_finder(maze, startCord);
		
		//Clears the result, null needs to be replaced by visited
		// for(int x=0; x<maze.length; x++)
		// 	for(int y=0; y<maze[x].length; y++)	
		// 		if(result[x][y] == null)
		// 			result[x][y] = " ";		
		// result[startCord.getX()][startCord.getY()] = "S";
		// result[endCord.getX()][endCord.getY()] = "E";

		print_result();
		
		return true;
	}
	
	public void print_result()
	{

		for(int x=0; x<result.size(); x++) {
			for(int y=0; y<result.get(x).size(); y++) {
				System.out.print(result.get(x).get(y) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
/*
 * returns true if path to exit is found, false otherwise.
*/
	private boolean path_finder(int[][] maze, Coordinate cord)
	{
		return false;
	}

	private Graph buildGraph(int[][] maze, Coordinate startCord, Coordinate endCord) {
		Graph graph = new Graph();
		Node start = new Node(startCord.getX(), startCord.getY(), false);
		graph.addNode(start);

		
		

		// for(int x=0; x<maze.length; x++)
		// 	for(int y=0; y<maze[x].length; y++)	
		// 		if(maze[x][y].equals("0"))
		// 			graph.addVertex(new Vertex(x, y));
		return graph;
	}

	// public void checkTraversable(Node n, int[][] maze){
	// 	//Check down
	// 	if(isTraversable(maze[n.getRow()][n.getCol() + 1])){
	// 		Node down = new Node(n.getRow(), n.getCol() + 1, maze[n.getRow()][n.getCol() + 1] == exitSymbol);

	// 	}
	// }
	public boolean isTraversable(int x){
		return x == 1;
	}

	public static void main(String args[]){
		MazeSolver mazeSolver = new MazeSolver();
		
		System.out.println(mazeSolver.isTraversable(1));

		

	}

}