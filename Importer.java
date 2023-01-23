package ProgrammingParadigmsProject;
import java.io.*;

public class Importer {

    public static void main(String[] args) throws IOException {

        String fileName = "ProgrammingParadigmsProject\\test.txt";
        importFromFile(fileName);
    }

    private static void importFromFile(String fileName) throws IOException {

        // Open the file

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;

        System.out.println("Importing from file " + fileName);
        while ((line = br.readLine()) != null) {
            // Process the line
            System.out.println("Processing line: " + line);
            // processLine(line);
        }
        br.close();
    }
}
