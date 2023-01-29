import java.util.ArrayList;

public class Maze {

	private Coordinate startCord, endCord;
	private ArrayList<ArrayList<Integer>> maze;

	public Maze(){
		this.maze = null;
		this.startCord = null;
		this.endCord = null;
	}

	public Maze(ArrayList<ArrayList<Integer>> maze, Coordinate startCord, Coordinate endCord) {
		this.maze = maze;
		this.startCord = startCord;
		this.endCord = endCord;
		
	}

	public void setMaze(ArrayList<ArrayList<Integer>> maze) {
		this.maze = maze;
	}

	public void setCoordinates(Coordinate startCord, Coordinate endCord) {
		this.startCord = startCord;
		this.endCord = endCord;
	}

	public ArrayList<ArrayList<Integer>> getMaze() {
		return maze;
	}

	public Coordinate getStartCord() {
		return startCord;
	}

	public Coordinate getEndCord() {
		return endCord;
	}
}

