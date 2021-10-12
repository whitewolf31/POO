package ex4;

import java.util.Random;
import java.util.Vector;

public class Problema4 {
    public static void main(String args[]) {
        Vector v1 = new Vector(10);
        Vector v2 = new Vector(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            v1.add(random.nextInt(20));
            v2.add(random.nextInt(20));
        }
        Problema4 problema4 = new Problema4();
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(problema4.difference(v1, v2));
    }

    private Vector union(Vector v1, Vector v2) {
        Vector v3 = new Vector();
        for (int i = 0; i < v1.size(); i++) {
            int element = (int) v1.get(i);
            if (!v3.contains(element)) v3.add(element);
        }
        for (int i = 0; i < v2.size(); i++) {
            int element = (int) v2.get(i);
            if (!v3.contains(element)) v3.add(element);
        }

        return v3;
    }

    private Vector intersection(Vector v1, Vector v2) {
        Vector v3 = new Vector();
        for (int i = 0; i < v1.size(); i++) {
            int element = (int) v1.get(i);
            if (v2.contains(element)) v3.add(element);
        }

        return v3;
    }

    private Vector difference(Vector v1, Vector v2) {
        Vector v3 = new Vector();
        for (int i = 0; i < v1.size(); i++) {
            int element = (int) v1.get(i);
            if (!v2.contains(element)) v3.add(element);
        }
        for (int i = 0; i < v2.size(); i++) {
            int element = (int) v2.get(i);
            if (!v1.contains(element)) v3.add(element);
        }

        return v3;
    }
}
