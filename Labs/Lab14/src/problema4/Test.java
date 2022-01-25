package problema4;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("test.out"));
            String line = br.readLine();
            while (line != null && !line.equals("exit")) {
                bw.write(line);
                line = br.readLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
