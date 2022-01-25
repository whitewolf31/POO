package problema5;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        int numberOfWords = 0;
        try {
            FileReader in = new FileReader(new File("src/test02.in"));
            StreamTokenizer str = new StreamTokenizer(in);
            int currentToken = str.nextToken();
            while (currentToken != StreamTokenizer.TT_EOF) {
                if (str.ttype == StreamTokenizer.TT_WORD) {
                    numberOfWords++;
                } else if (str.ttype == StreamTokenizer.TT_NUMBER) {
                    numberOfWords++;
                }

                currentToken = str.nextToken();
            }
            System.out.println(numberOfWords);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
