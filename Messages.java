import java.util.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

//Put all print statements here
public class Messages {

    Scanner sc = new Scanner(System.in);

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

    public void noFileGiven(){
        System.out.println("No file given, quitting!\n");
    }

    public void printGraph(Map<Node, List<Node>> mazeGraph, Node startNode, Node endNode) {
        for (Node n : mazeGraph.keySet()) {
            System.out.println(n + " : " + mazeGraph.get(n));
        }
    }

    public void printWelome(){
        AsciiArt asciiArt = new AsciiArt();
        System.out.println("\n");
        asciiArt.drawString("Maze Solver!", "*", asciiArt.new Settings(new Font("Times New Roman", Font.PLAIN, 20), 125, 25));
        System.out.println("\n");
    }

    /*AsciiArt adapted from https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-console/src/main/java/com/baeldung/asciiart/AsciiArt.java */
    public class AsciiArt {

        public AsciiArt() {
        }
    
        public void drawString(String text, String artChar, Settings settings) {
            BufferedImage image = getImageIntegerMode(settings.width, settings.height);
    
            Graphics2D graphics2D = getGraphics2D(image.getGraphics(), settings);
            graphics2D.drawString(text, 6, ((int) (settings.height * 0.67)));
    
            for (int y = 0; y < settings.height; y++) {
                StringBuilder stringBuilder = new StringBuilder();
    
                for (int x = 0; x < settings.width; x++) {
                    stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : Globals.ANSI_GREEN +  artChar + Globals.TEXT_RESET);
                }
    
                if (stringBuilder.toString()
                    .trim()
                    .isEmpty()) {
                    continue;
                }
    
                System.out.println(stringBuilder);
            }
    
        }
    
        private BufferedImage getImageIntegerMode(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }
    
        private Graphics2D getGraphics2D(Graphics graphics, Settings settings) {
            graphics.setFont(settings.font);
    
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    
            return graphics2D;
        }
    
        public class Settings {
            public Font font;
            public int width;
            public int height;
    
            public Settings(Font font, int width, int height) {
                this.font = font;
                this.width = width;
                this.height = height;
            }
        }
    }
}
