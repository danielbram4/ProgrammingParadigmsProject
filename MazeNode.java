package ProgrammingParadigmsProject;

public class MazeNode {

    private boolean traversable;
    private MazeNode left;
    private MazeNode right;
    private MazeNode up;
    private MazeNode down;

    public MazeNode(boolean traversable, MazeNode left, MazeNode right, MazeNode up, MazeNode down){
        this.traversable = traversable;
        this. left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public MazeNode(){
        traversable = false;
        left = null;
        right = null;
        up = null;
        down = null;
    }

    //--------------GETTERS------------------------//

    public boolean isTraversable(){
        return traversable;
    }

    public MazeNode getLeft(){
        return left;
    }

    public MazeNode getRight(){
        return right;
    }

    public MazeNode getUp(){
        return up;
    }

    public MazeNode getDown(){
        return down;
    }

    //--------------SETTERS------------------------//

    public void setTraversable(boolean traversable){
        this.traversable = traversable;
    }

    public void setLeft(MazeNode left){
        this.left = left;
    }

    public void setRight(MazeNode right){
        this.right = right;
    }

    public void setUp(MazeNode up){
        this.up = up;
    }

    public void setDown(MazeNode down){
        this.down = down;
    }
}
