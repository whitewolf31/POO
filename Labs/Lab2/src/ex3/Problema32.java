package ex3;

import java.util.Random;
import java.util.Vector;

public class Problema32 {
    public static void main(String args[]) {
        Vector v = new Vector(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) v.add(random.nextInt(10));
        int max = 0;
        int min = 10;
        int sum = 0;
        for (int i = 0; i < 20; i++) {
            int element = (int) v.get(i);
            if (element > max) max = element;
            if (element < min) min = element;
            sum += element;
        }
        System.out.println("Max is " + max);
        System.out.println("Min is " + min);
        float mean = (float) sum / 20;
        System.out.println("Mean is " + mean);
    }
}
