package ex8;

import java.util.Arrays;

public class Problema8 {
    public static void main(String args[]) {
        Problema8 problema8 = new Problema8();
        double v[] = new double[100];
        for (int i = 0; i < 100; i++) {
            v[i] = Math.random() * 10; // between 0 and 10
        }
        Arrays.sort(v);
        problema8.isInArray(v, 5.23);
        problema8.isInArray(v, v[23]);
    }

    public void isInArray(double v[], double n) {
        int test = Arrays.binarySearch(v, n);
        if (test >= 0) System.out.println(n + " is in array");
        else System.out.println(n + " is not in array");
    }
}
