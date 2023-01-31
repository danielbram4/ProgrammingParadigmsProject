/*Class representing a single node in a graph
implementation information found at https://www.baeldung.com/java-graphs*/

public class Node {
    private Coordinate coordinates = new Coordinate();
    private int distance;
    private Node previous;

    public Node(int r, int c) {
        coordinates.setRow(r);
        coordinates.setCol(c);
        distance = Integer.MAX_VALUE;
        previous = null;
    }


    public Node(){
        
    }
    
    public int getRow() {
        return coordinates.getRow();
    }

    public int getCol() {
        return coordinates.getCol();
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
