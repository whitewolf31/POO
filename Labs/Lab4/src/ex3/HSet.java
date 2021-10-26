package ex3;

import java.util.Enumeration;
import java.util.Hashtable;

public class HSet extends Hashtable {

    public HSet() {}

    public boolean add(Object value) {
        if (super.contains(value)) return false;
        super.put(value, value);

        return true;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Enumeration e = super.keys(); e.hasMoreElements(); ) {
            s.append(e.nextElement() + "\n");
        }

        return s.toString();
    }

    public Object remove(Object key) {
        return super.remove(key);
    }
}

class Test3 {
    public static void main(String args[]) {
        HSet set = new HSet();
        set.add("Laborator");
        set.add("Agregare");
        set.add("Mostenire");
        System.out.println(set);
        System.out.println(set.size());
        set.add("Laborator");
        if(set.size() == 4) {
            System.out.println("Multimea nu trebuie sa contina duplicate!");
        }
        System.out.println(set.remove("POO"));
        System.out.println(set.remove("Laborator"));
        if(set.size() != 2) {
            System.out.println("Stergerea nu functioneaza!");
        }
        set.add("Supradefinire");
        set.add("Supraincarcare");
        System.out.println(set);
    }
}