/*Class representing a single node in a graph
implementation information found at https://www.baeldung.com/java-graphs*/

public class Node {
    private Coordinate coordinates = new Coordinate();

    public Node(int r, int c) {
        coordinates.setRow(r);
        coordinates.setCol(c);
    }

    public Node(){
        
    }
    
    public int getRow() {
        return coordinates.getRow();
    }

    public int getCol() {
        return coordinates.getCol();
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int r, int c) {
        this.coordinates = new Coordinate(r, c);
    }

    public String toString() {
        return "(" + coordinates.getRow() + ", " + coordinates.getCol() + ") ";
    }
}
