import java.util.ArrayList;

public class Maze {

	// Fields representing the starting coordinate, ending coordinate, and the maze
	private Coordinate startCord, endCord;
	private ArrayList<ArrayList<Integer>> maze;

	// Default constructor with no parameters
	public Maze(){
		this.maze = null;
		this.startCord = null;
		this.endCord = null;
	}

	// Constructor with parameters maze, startCord, and endCord
	public Maze(ArrayList<ArrayList<Integer>> maze, Coordinate startCord, Coordinate endCord) {
		this.maze = maze;
		this.startCord = startCord;
		this.endCord = endCord;
		
	}

	// Setter method for the maze field
	public void setMaze(ArrayList<ArrayList<Integer>> maze) {
		this.maze = maze;
	}

	// Setter method for the startCord and endCord fields
	public void setCoordinates(Coordinate startCord, Coordinate endCord) {
		this.startCord = startCord;
		this.endCord = endCord;
	}

	// Getter method for the maze field
	public ArrayList<ArrayList<Integer>> getMaze() {
		return maze;
	}

	// Getter method for the startCord field
	public Coordinate getStartCord() {
		return startCord;
	}

	// Getter method for the endCord field
	public Coordinate getEndCord() {
		return endCord;
	}

}

