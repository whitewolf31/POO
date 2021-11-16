package ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SListSet extends LinkedList implements SortedSet {
    Comparator comp;

    public static void main(String[] args) {
        SListSet s1 = new SListSet();
        SListSet s2 = new SListSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o2).compareTo((String) o1);
            }
        });
        try {
            File file = new File("test01.txt");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[^a-zA-Z]+");
            while (scanner.hasNext()) {
                String currentWord = scanner.next();
                s1.add(currentWord);
                s2.add(currentWord);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        System.out.println(s1);
        System.out.println(s2);
    }

    public SListSet() {
        this(Comparator.naturalOrder());
    }

    public SListSet(Comparator c) {
        comp = c;
    }

    @Override
    public boolean add(Object o) {
        if (!contains(o)) {
            super.add(o);
            sort(comp);
        }

        return false;
    }

    @Override
    public Comparator comparator() {
        return comp;
    }

    @Override
    public SortedSet subSet(Object fromElement, Object toElement) {
        return (SListSet) subList(indexOf(fromElement), indexOf(toElement));
    }

    @Override
    public SortedSet headSet(Object toElement) {
        return (SListSet) subList(0, indexOf(toElement));
    }

    @Override
    public SortedSet tailSet(Object fromElement) {
        return (SListSet) subList(indexOf(fromElement), size() - 1);
    }

    @Override
    public Object first() {
        if (size() == 0) return null;
        return get(0);
    }

    @Override
    public Object last() {
        if (size() == 0) return null;
        return get(size() - 1);
    }
}
