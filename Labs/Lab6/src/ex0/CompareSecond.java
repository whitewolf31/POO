package ex0;

import java.util.Comparator;

public class CompareSecond implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Pair castO1 = (Pair) o1;
        Pair castO2 = (Pair) o2;

        Comparable firstValue = (Comparable) castO1.value2;
        Comparable secondValue = (Comparable) castO2.value2;

        return firstValue.compareTo(secondValue);
    }
}
