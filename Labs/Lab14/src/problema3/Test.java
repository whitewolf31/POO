package problema3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Test {
    public static void main(String[] args) {
        try {
            LineNumberReader reader = new LineNumberReader(new FileReader("src/test01.in"));
            try {
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    int line = reader.getLineNumber() - 1;
                    if (line % 2 == 1) System.out.println(line + ") " + currentLine);
                    currentLine = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
