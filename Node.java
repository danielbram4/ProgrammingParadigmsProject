/*Class representing a single node in a graph
implementation information found at https://www.baeldung.com/java-graphs*/

public class Node {
    private Integer[] tuple = new Integer[2];

    public Node(int r, int c) {
        tuple[0] = r;
        tuple[1] = c;
    }
    
    public int getRow() {
        return tuple[0];
    }

    public int getCol() {
        return tuple[1];
    }

    public Integer[] getTuple() {
        return tuple;
    }

    public void setRow(int r) {
        tuple[0] = r;
    }

    public void setCol(int c) {
        tuple[1] = c;
    }

    public void setTuple(Integer[] t) {
        tuple = t;
    }

    public String toString() {
        return "(" + tuple[0] + ", " + tuple[1] + ")";
    }
}
