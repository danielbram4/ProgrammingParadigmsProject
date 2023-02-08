import java.util.*;

//Put all print statements here
public class Messages {
    public void PATHFOUND() {
        System.out.print("Hello");
    }

    public void PATHNOTFOUND() {
        System.out.println("Path to exit was not found.");
    }

    public void ENTERFILENAME() {
        System.out.print("Enter the filename: ");
    }

    public void NOTVALIDFILE() {
        System.out.println("Improper text file, maze not generated!");
    }

    public void FILENOTFOUND() {
        System.out.println("The file was not found, quitting!\n");
    }

    public void printGraph(Map<Node, List<Node>> mazeGraph, Node startNode, Node endNode) {
        for (Node n : mazeGraph.keySet()) {
            System.out.println(n + " : " + mazeGraph.get(n));
        }

        System.out.println("Start Node: " + startNode);
        System.out.println("End Node: " + endNode);
    }
}
