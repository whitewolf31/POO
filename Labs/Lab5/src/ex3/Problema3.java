package ex3;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Problema3 {
    Vector<Integer> v = new Vector<Integer>();

    public void myRead() throws NumarNegativ {
        Scanner s = new Scanner(System.in);
        while (true) {
            int currentNumber = s.nextInt();
            if (currentNumber < 0) {
                s.close();
                throw new NumarNegativ();
            }
            else {
                v.add(currentNumber);
            }
        }
    }

    public int getMax() {
        return Collections.max(v);
    }

    public static void main(String args[]) {
        Problema3 test = new Problema3();
        try {
            test.myRead();
        } catch (NumarNegativ e) {
            e.printStackTrace();
        } finally {
            System.out.println("Max is " + test.getMax());
        }
    }
}
