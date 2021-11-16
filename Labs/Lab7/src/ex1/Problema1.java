package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Problema1 {
    public static void main(String[] args) {
        Problema1 test = new Problema1();
        TreeSet ts1 = test.printWords("test01.txt");
        System.out.println("---------------------");
        TreeSet ts2 = test.printWordsReverse(ts1);
    }

    public TreeSet printWords(String fileName) {
        TreeSet<String> ts = new TreeSet<String>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[^a-zA-Z]+");
            while (scanner.hasNext()) {
                String currentWord = scanner.next();
                ts.add(currentWord);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        ts.forEach(System.out::println);

        return ts;
    }

    public TreeSet printWordsReverse(TreeSet normalOrderTs) {
        TreeSet<String> reverseTs = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        reverseTs.addAll(normalOrderTs);
        reverseTs.forEach(System.out::println);

        return reverseTs;
    }
}
