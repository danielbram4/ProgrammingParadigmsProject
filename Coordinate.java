public class Coordinate {
	private int r, c;
	
	public Coordinate() {
		this.r = -1;
		this.c = -1;
	}

	public Coordinate(int r, int c)
	{
		this.r = r;
		this.c = c;
	}
	
	public int getRow() {
		return r;
	}
	
	public int getCol() {
		return c;
	}
	
	public void setRow(int r) {
		this.r = r;
	}
	
	public void setCol(int c) {
		this.c = c;
	}
	
	@Override
	public String toString() {
		return "(r, c) = (" + r + ", " + c + ")";
	}
}