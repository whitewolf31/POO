package ex6;

import java.util.HashMap;
import java.util.Vector;

public class Problema6 {
    public static void main(String args[]) {
        Vector v1 = new Vector();
        v1.add(7.5);
        v1.add("String");
        v1.add(true);
        v1.add(false);
        v1.add("Another String");
        v1.add(80);
        v1.add(15);
        HashMap<String, Integer> map = new HashMap();
        for (int i = 0; i < v1.size(); i++) {
            String className = v1.get(i).getClass().getName();
            if (map.containsKey(className)) {
                Integer x = map.get(className);
                x++;
                map.put(className, x);
            } else {
                map.put(className, 1);
            }
        }
        for (String i: map.keySet()) {
            System.out.println(i + ": " + map.get(i));
        }
    }
}
