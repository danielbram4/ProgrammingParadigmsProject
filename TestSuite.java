
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;


public class TestSuite {

    GraphBuilder graphBuilder = new GraphBuilder();
    MazeBuilder mb = new MazeBuilder();

    private Path workingDir;

    @Before
    public void init(){
        this.workingDir = Path.of("C:\\Users\\scott\\OneDrive\\Documents\\Programming Paradigms\\ProjectPart1\\ProgrammingParadigmsProject");
    }


    /*Tests the Maze and MazeBuilder Class */
    @Test
    public void testMazeBuilder() {
        String fileName = this.workingDir.resolve("Tests/test0.txt").toString();
        System.out.println(fileName);
        Coordinate testStart = new Coordinate(1, 1);
        Coordinate testEnd = new Coordinate(6, 4);

        ArrayList<ArrayList<Integer>> expectedMaze = getExpectedMaze0();

        Maze testMaze = mb.buildMaze(fileName);

        assertEquals(testStart.getRow(), testMaze.getStartCord().getRow());
        assertEquals(testStart.getCol(), testMaze.getStartCord().getCol());
        assertEquals(testEnd.getRow(), testMaze.getEndCord().getRow());
        assertEquals(testEnd.getCol(), testMaze.getEndCord().getCol());

        for(int i = 0; i < expectedMaze.size(); i++){
            for(int j = 0; j < expectedMaze.get(i).size(); j++){
                assertEquals(expectedMaze.get(i).get(j), testMaze.getMaze().get(i).get(j));
            }
        }
    }

    private ArrayList<ArrayList<Integer>> getExpectedMaze0() {
        ArrayList<ArrayList<Integer>> expectedMaze = new ArrayList<ArrayList<Integer>>();
        
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        ArrayList<Integer> row2 = new ArrayList<Integer>();
        row2.add(0);
        row2.add(8);
        row2.add(0);
        row2.add(0);
        row2.add(0);
        row2.add(0);
        row2.add(0);
        row2.add(0);
        ArrayList<Integer> row3 = new ArrayList<Integer>();
        row3.add(0);
        row3.add(1);
        row3.add(0);
        row3.add(0);
        row3.add(0);
        row3.add(0);
        row3.add(0);
        row3.add(0);
        ArrayList<Integer> row4 = new ArrayList<Integer>();
        row4.add(0);
        row4.add(1);
        row4.add(1);
        row4.add(1);
        row4.add(1);
        row4.add(0);
        row4.add(0);
        row4.add(0);
        ArrayList<Integer> row5 = new ArrayList<Integer>();
        row5.add(0);
        row5.add(0);
        row5.add(0);
        row5.add(0);
        row5.add(1);
        row5.add(0);
        row5.add(0);
        row5.add(0);
        ArrayList<Integer> row6 = new ArrayList<Integer>();
        row6.add(0);
        row6.add(0);
        row6.add(0);
        row6.add(0);
        row6.add(1);
        row6.add(0);
        row6.add(0);
        row6.add(0);
        ArrayList<Integer> row7 = new ArrayList<Integer>();
        row7.add(0);
        row7.add(0);
        row7.add(0);
        row7.add(0);
        row7.add(1);
        row7.add(0);
        row7.add(0);
        row7.add(0);
        ArrayList<Integer> row8 = new ArrayList<Integer>();
        row8.add(0);
        row8.add(0);
        row8.add(0);
        row8.add(0);
        row8.add(0);
        row8.add(0);
        row8.add(0);
        row8.add(0);

        expectedMaze.add(row1);
        expectedMaze.add(row2);
        expectedMaze.add(row3);
        expectedMaze.add(row4);
        expectedMaze.add(row5);
        expectedMaze.add(row6);
        expectedMaze.add(row7);
        expectedMaze.add(row8);

        return expectedMaze;
    }
    
    @Test
    public void testGraph() {
        Node testNode = new Node(1, 1);
        Node diagonalNode = new Node(2, 2);
        Node testNode2 = new Node(1, 2);
        Node testNode3 = new Node(5,6);
        Node testNode4 = new Node(5,7);

        Graph testGraph = new Graph();

        testGraph.addNode(testNode);
        testGraph.addNode(testNode2);
        testGraph.addNode(testNode3);
        testGraph.addNode(diagonalNode);

        assertEquals(true, testGraph.checkIfNodeExists(testNode3));

        assertEquals(true, testGraph.isDiagonal(testNode, diagonalNode));
        assertEquals(false, testGraph.isDiagonal(testNode, testNode2));

        assertEquals(true, testGraph.isAdjacent(testNode, testNode2));
        assertEquals(false, testGraph.isAdjacent(testNode, testNode3));


        assertEquals(true, testGraph.containsNode(testNode));
        assertEquals(true, testGraph.containsNode(testNode2));
        assertEquals(true, testGraph.containsNode(testNode3));
        assertEquals(false, testGraph.containsNode(testNode4));

        testGraph.addEdge(testNode, testNode2);
        testGraph.addEdge(testNode, testNode4);

        assertEquals(true, testGraph.checkIfEdgeExists(testNode, testNode2));
        assertEquals(false, testGraph.checkIfEdgeExists(testNode, testNode4));

        Node getNode = testGraph.getNode(1,1);

        assertEquals(testNode, getNode);


        testGraph.setStartNode(testNode);
        testGraph.setEndNode(diagonalNode);

        assertEquals(testNode, testGraph.getStartNode());
        assertEquals(diagonalNode, testGraph.getEndNode());
    }

    @Test
    public void testGraphBuilder(){
        ArrayList<ArrayList<Integer>> testMaze = getExpectedMaze0();
        Maze testMazeObject = new Maze(testMaze, new Coordinate(1,1), new Coordinate(6,4));
        Graph testGraph = graphBuilder.buildGraph(testMazeObject);

        assertEquals(1, testGraph.getStartNode().getRow());
        assertEquals(1, testGraph.getStartNode().getCol());
        assertEquals(6, testGraph.getEndNode().getRow());
        assertEquals(4, testGraph.getEndNode().getCol());
    
    }

}
