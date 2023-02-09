
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;

public class TestSuite {

    GraphBuilder graphBuilder = new GraphBuilder();
    MazeBuilder mb = new MazeBuilder();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Path workingDir;

    @Before
    public void init() {
        this.workingDir = Path.of(
                "C:\\Users\\scott\\OneDrive\\Documents\\Programming Paradigms\\ProjectPart1\\ProgrammingParadigmsProject");

    }

    /* White box tests test for the individual modules of the system */
    @Test
    public void testMazeBuilder() {
        String fileName = this.workingDir.resolve("Tests/test0.txt").toString();
        Coordinate testStart = new Coordinate(1, 1);
        Coordinate testEnd = new Coordinate(6, 4);

        ArrayList<ArrayList<Integer>> expectedMaze = getExpectedMaze0();

        Maze testMaze = mb.buildMaze(fileName);

        assertEquals(testStart.getRow(), testMaze.getStartCord().getRow());
        assertEquals(testStart.getCol(), testMaze.getStartCord().getCol());
        assertEquals(testEnd.getRow(), testMaze.getEndCord().getRow());
        assertEquals(testEnd.getCol(), testMaze.getEndCord().getCol());

        for (int i = 0; i < expectedMaze.size(); i++) {
            for (int j = 0; j < expectedMaze.get(i).size(); j++) {
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
        Node testNode3 = new Node(5, 6);
        Node testNode4 = new Node(5, 7);

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

        Node getNode = testGraph.getNode(1, 1);

        assertEquals(testNode, getNode);

        testGraph.setStartNode(testNode);
        testGraph.setEndNode(diagonalNode);

        assertEquals(testNode, testGraph.getStartNode());
        assertEquals(diagonalNode, testGraph.getEndNode());
    }

    @Test
    public void testGraphBuilder() {
        ArrayList<ArrayList<Integer>> testMaze = getExpectedMaze0();
        Maze testMazeObject = new Maze(testMaze, new Coordinate(1, 1), new Coordinate(6, 4));
        Graph testGraph = graphBuilder.buildGraph(testMazeObject);
        Graph expectedGraph = getExpectedGraph0();

        assertEquals(1, testGraph.getStartNode().getRow());
        assertEquals(1, testGraph.getStartNode().getCol());
        assertEquals(6, testGraph.getEndNode().getRow());
        assertEquals(4, testGraph.getEndNode().getCol());

        expectedGraph.getMazeGraph().forEach((key, value) -> {
            assertEquals(true, testGraph.getGraph().checkIfNodeExists(key));
        });
    }

    private Graph getExpectedGraph0() {
        Node n1 = new Node(1, 1);
        Node n2 = new Node(2, 1);
        Node n3 = new Node(3, 1);
        Node n4 = new Node(3, 2);
        Node n5 = new Node(3, 3);
        Node n6 = new Node(3, 4);
        Node n7 = new Node(4, 4);
        Node n8 = new Node(5, 4);
        Node n9 = new Node(6, 4);

        Graph expectedGraph = new Graph();

        expectedGraph.addNode(n1);
        expectedGraph.addNode(n2);
        expectedGraph.addNode(n3);
        expectedGraph.addNode(n4);
        expectedGraph.addNode(n5);
        expectedGraph.addNode(n6);
        expectedGraph.addNode(n7);
        expectedGraph.addNode(n8);
        expectedGraph.addNode(n9);

        expectedGraph.addEdge(n1, n2);
        expectedGraph.addEdge(n2, n3);
        expectedGraph.addEdge(n3, n4);
        expectedGraph.addEdge(n4, n5);
        expectedGraph.addEdge(n5, n6);
        expectedGraph.addEdge(n6, n7);
        expectedGraph.addEdge(n7, n8);
        expectedGraph.addEdge(n8, n9);

        expectedGraph.setStartNode(n1);
        expectedGraph.setEndNode(n9);

        return expectedGraph;
    }

    @Test
    public void testDFS() {
        ArrayList<ArrayList<Integer>> testMaze = getExpectedMaze0();
        Maze testMazeObject = new Maze(testMaze, new Coordinate(1, 1), new Coordinate(6, 4));
        Graph testGraph = graphBuilder.buildGraph(testMazeObject);

        ArrayList<Node> expectedPath = getExpectedPath0();
        ArrayList<Node> actualPath = testGraph.findPath(testGraph.getStartNode(), testGraph.getEndNode());

        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(actualPath.get(i).getRow(), expectedPath.get(i).getRow());
            assertEquals(actualPath.get(i).getCol(), expectedPath.get(i).getCol());
        }
    }

    private ArrayList<Node> getExpectedPath0() {
        ArrayList<Node> expectedPath = new ArrayList<Node>();
        expectedPath.add(new Node(2, 1));
        expectedPath.add(new Node(3, 1));
        expectedPath.add(new Node(3, 2));
        expectedPath.add(new Node(3, 3));
        expectedPath.add(new Node(3, 4));
        expectedPath.add(new Node(4, 4));
        expectedPath.add(new Node(5, 4));
        expectedPath.add(new Node(6, 4));

        return expectedPath;
    }

    /* Black Box testing tests the expcted output of various mazes */
    @Test
    public void shouldSolvePathAndOnlyWalls() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test0.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # \n# [34mS[0m # # # # # # \n# [32mX[0m # # # # # # \n# [32mX[0m [32mX[0m [32mX[0m [32mX[0m # # # \n# # # # [32mX[0m # # # \n# # # # [32mX[0m # # # \n# # # # [34mE[0m # # # \n# # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldSolveCycleAndOnlyWalls() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test1.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # \n# [34mS[0m       # # # \n# [32mX[0m # #   # # # \n# [32mX[0m [32mX[0m [32mX[0m [32mX[0m # # # \n# # # # [32mX[0m # # # \n# # # # [32mX[0m # # # \n# # # # [34mE[0m # # # \n# # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldSolveComplexPerfectMaze() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test2.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # # # \n# [34mS[0m [32mX[0m [32mX[0m           # \n#   # [32mX[0m # # # # # # \n#   # [32mX[0m [32mX[0m         # \n#   # # [32mX[0m #   # # # \n#   #   [32mX[0m #   #   # \n#   #   [32mX[0m [32mX[0m       # \n#   # # # [32mX[0m # # # # \n# #       [32mX[0m [32mX[0m [32mX[0m [34mE[0m # \n# # # # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldSolveComplexImperfectMaze() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test3.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # # # \n# [34mS[0m               # \n# [32mX[0m #   # # # # # # \n# [32mX[0m #             # \n# [32mX[0m # #   #   # # # \n# [32mX[0m #     #   #   # \n# [32mX[0m #             # \n# [32mX[0m # # #   # # # # \n# [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [34mE[0m # \n# # # # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNoExitIsGiven() {
        assertThrows(NullPointerException.class, () -> {
            Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test4.txt").toString());
            SymbolConverter converter = new SymbolConverter(maze);
            Graph graph = graphBuilder.buildGraph(maze);
            ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());
        });
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenFileIsEmpty() {
        assertThrows(NullPointerException.class, () -> {
            Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test5.txt").toString());
            SymbolConverter converter = new SymbolConverter(maze);
            Graph graph = graphBuilder.buildGraph(maze);
            ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());
        });
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenFileOnlyWalls() {
        assertThrows(NullPointerException.class, () -> {
            Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test6.txt").toString());
            SymbolConverter converter = new SymbolConverter(maze);
            Graph graph = graphBuilder.buildGraph(maze);
            ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());
        });
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenFileOnlyPaths() {
        assertThrows(NullPointerException.class, () -> {
            Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test7.txt").toString());
            SymbolConverter converter = new SymbolConverter(maze);
            Graph graph = graphBuilder.buildGraph(maze);
            ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());
        });
    }

    @Test
    public void shouldPickLastExitInTheMaze() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test8.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # # # \n# [34mS[0m               # \n# [32mX[0m #   # # # # # # \n# [32mX[0m #   [32mX[0m [32mX[0m [32mX[0m [32mX[0m [34mE[0m # \n# [32mX[0m # # [32mX[0m #   # # # \n# [32mX[0m #   [32mX[0m #   #   # \n# [32mX[0m #   [32mX[0m [32mX[0m       # \n# [32mX[0m # # # [32mX[0m # # # # \n# [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m       # \n# # # # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldBuildPathAroundAdjacentStartAndEnd() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test9.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # # # \n# [34mS[0m [34mE[0m [32mX[0m           # \n# [32mX[0m # [32mX[0m # # # # # # \n# [32mX[0m # [32mX[0m [32mX[0m         # \n# [32mX[0m # # [32mX[0m #   # # # \n# [32mX[0m #   [32mX[0m #   #   # \n# [32mX[0m     [32mX[0m [32mX[0m       # \n# [32mX[0m # # # [32mX[0m # # # # \n# [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m       # \n# # # # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldSolveMazeWithCycles() {
        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test10.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);
        assertEquals(
                "# # # # # # # # \n# [34mS[0m [32mX[0m     # # # \n# # [32mX[0m #   # # # \n# # [32mX[0m [32mX[0m [32mX[0m # # # \n# # # # [32mX[0m [32mX[0m [32mX[0m # \n# # # # # # [32mX[0m # \n# # # # [34mE[0m [32mX[0m [32mX[0m # \n# # # # # # # # \n\n",
                outContent.toString());
    }

    @Test
    public void shouldThrowNullPointerWhenNoPathIsFound() {

        assertThrows(NullPointerException.class, () -> {
            Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test11.txt").toString());
            SymbolConverter converter = new SymbolConverter(maze);
            Graph graph = graphBuilder.buildGraph(maze);
            ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

            System.setOut(new PrintStream(outContent));
            converter.printSolvedMaze(path);

        });

    }

    @Test
    public void shouldSolveWhenEndIsAtTopAndStartIsAtBottom() {

        Maze maze = mb.buildMaze(this.workingDir.resolve("Tests/test12.txt").toString());
        SymbolConverter converter = new SymbolConverter(maze);
        Graph graph = graphBuilder.buildGraph(maze);
        ArrayList<Node> path = graph.findPath(graph.getStartNode(), graph.getEndNode());

        System.setOut(new PrintStream(outContent));
        converter.printSolvedMaze(path);

        assertEquals(
                "# # # # # # # # # # # # # # # # \n# [34mE[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m # \n#   # # # # # # # # # # # # [32mX[0m # \n#           #               [32mX[0m # \n#   # # # # # # # # # # # # [32mX[0m # \n#           #           #   [32mX[0m # \n#   # # # # #   # #   # # # [32mX[0m # \n#           #   # [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m [32mX[0m # \n# # # #     #   # [32mX[0m   # # #   # \n#           # # [34mS[0m [32mX[0m     #     # \n# # # # # # # # # # # # # # # # \n\n",
                outContent.toString());

    }

}
