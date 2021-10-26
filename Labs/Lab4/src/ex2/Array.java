package ex2;

import java.util.Collections;
import java.util.Vector;

public class Array {
    //Vectorul in care se vor retine elementele
    private Vector vector;

    //Constructor clasei
    public Array() {
        //Instantierea vectorului cu elemente
        vector = new Vector();
    }

    //Metoda care adauga un element in vector, folosind pozitia curenta
    public void addElement(Integer x) {
        vector.add(x);
    }

    //Metoda care adauga un element in vector, tinand cont de pozitia indicata
    public void addElement(Integer x, int poz) {
        if(poz >= 0 && poz <= vector.size()) {
            vector.add(poz, x);
        }
    }

    //Metoda care returneaza elementul aflat in vector la pozitia indicata
    public int get(int poz) {
        int result;
        if(poz >= 0 && poz < vector.size()) {
            result = (int) vector.get(poz);
            return result;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    //Metoda ce intoarce numarul de elemente din vector
    public int getSize() {
        return vector.size();
    }

    //Metoda pentru stergerea unui element din vector
    public boolean remove(Integer x) {
        return vector.remove(x);
    }

    //Metoda pentru stergerea elementului de pe pozitia pos din vector
    public Integer remove(int pos) {
        return (Integer) vector.remove(pos);
    }

    //Metoda uzitata pentru afisarea unui obiect de tip Array
    public String toString() {
        String result = "{";
        for(int i = 0; i < vector.size(); i++) {
            result += get(i) + ", ";
        }
        result += "}";
        return result;
    }

    public void sort() {
        Collections.sort(vector);
    }

    public static void main(String args[]) {
        MyStack s = new MyStack();
        s.push(3);
        s.push(5);
        s.push(6);
        System.out.println(s.pop());
        System.out.println(s.pop());
        s.push(10);
        s.push(15);
        System.out.println(s.pop());
    }
}

class SortedArray extends Array {

    private Vector vector;

    public SortedArray() {}

    @Override
    public void addElement(Integer x) {
        super.addElement(x);
        super.sort();
    }

    @Override
    public void addElement(Integer x, int poz) {
        super.addElement(x, poz);
        super.sort();
    }

}

class MyStack {
    private Array arr;

    public MyStack() {
        arr = new Array();
    }

    public void push(Integer x) {
        arr.addElement(x);
    }

    public int pop() {
        int size = arr.getSize();
        int returnValue = arr.get(size - 1);
        arr.remove(size - 1);

        return returnValue;
    }
}
