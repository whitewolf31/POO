package ex3;

import java.util.Random;
import java.util.Vector;

public class Problema31 {
    public static void main(String args[]) {
        Vector v = new Vector(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) v.add(random.nextInt(10));
        System.out.println(v);
        System.out.println(Problema31.eliminateAndCount(v, 5));
    }

    public static int eliminateAndCount(Vector v, int x) {
        int idx = v.indexOf(x);
        int counter = 0;
        while (idx >= 0) {
            counter++;
            v.remove(idx);
            idx = v.indexOf(x);
        }

        return counter;
    }
}
