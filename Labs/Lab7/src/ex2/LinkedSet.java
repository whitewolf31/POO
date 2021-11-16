package ex2;

import java.util.LinkedList;
import java.util.Set;

public class LinkedSet extends LinkedList implements Set {
    @Override
    public boolean add(Object obj) {
        if (!contains(obj)) return super.add(obj);

        return false;
    }

    public void add(int i, Object obj) {
        if (!contains(obj)) {
            super.add(i, obj);
        }
    }

    public Object set(int i, Object obj) {
        if (!contains(obj)) {
            return super.set(i, obj);
        }

        return super.get(i);
    }
}
