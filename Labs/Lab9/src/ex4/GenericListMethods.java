package ex4;

import java.util.ArrayList;
import java.util.Collections;

public class GenericListMethods implements GenericInterface {

    public static void main(String[] args) {
        ArrayList<Integer> testList = new ArrayList();
        testList.add(2);
        testList.add(4);
        testList.add(4);
        testList.add(5);
        testList.add(7);
        testList.add(7);
        testList.add(7);
        testList.add(9);
        testList.add(13);
        GenericListMethods methods = new GenericListMethods();
        // System.out.println(methods.binarySearch(testList, 9, 0, testList.size() - 1));
        // System.out.println(methods.max(testList));
        // System.out.println(methods.removeDuplicates(testList));
    }

    @Override
    public <E extends Comparable<E>> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList();
        for (E element: list) {
            if (!newList.contains(element)) newList.add(element);
        }

        return newList;
    }

    @Override
    public <E extends Comparable<E>> E max(ArrayList<E> list) {
        return Collections.max(list);
    }

    @Override
    public <E extends Comparable<E>> int binarySearch(ArrayList<E> list, E key, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) / 2;
        if (list.get(mid).equals(key)) return mid;
        if (list.get(mid).compareTo(key) > 0) return binarySearch(list, key, start, mid);
        if (list.get(mid).compareTo(key) < 0) return binarySearch(list, key, mid, end);

        return -1;
    }
}
