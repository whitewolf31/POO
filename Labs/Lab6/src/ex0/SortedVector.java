package ex0;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SortedVector extends Vector {
    Comparator comparator;

    public SortedVector(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(Object o) {
        boolean res = super.add(o);
        if (res) this.sort();

        return res;
    }

    public void sort() {
        Collections.sort(this, comparator);
    }

    public static void main(String[] args) {
        Pair pair1 = new Pair("laur", 2);
        Pair pair2 = new Pair("alex", 5);
        Pair pair3 = new Pair("tudor", 3);
        Comparator compareFirst = new CompareFirst();
        Comparator compareSecond = new CompareSecond();
        SortedVector v1 = new SortedVector(compareFirst);
        SortedVector v2 = new SortedVector(compareSecond);
        v1.add(pair1);
        v1.add(pair2);
        v1.add(pair3);
        v2.add(pair1);
        v2.add(pair2);
        v2.add(pair3);
        v1.sort();
        v2.sort();
        System.out.println(v1);
        System.out.println(v2);
    }

}
