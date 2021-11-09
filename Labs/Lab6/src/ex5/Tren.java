package ex5;

import java.util.Vector;

public class Tren {
    Vector<Vagon> vagoane;

    public Tren() {
        vagoane = new Vector<Vagon>();
    }

    public void addVagon(Vagon v) {
        vagoane.add(v);
    }

    public static void main(String args[]) {
        Tren t = new Tren();
        t.addVagon(new CalatoriA());
        t.addVagon(new CalatoriB());
        t.addVagon(new CalatoriA());
        t.addVagon(new CalatoriA());
        t.addVagon(new CalatoriA());
        t.addVagon(new CalatoriB());
        t.addVagon(new CalatoriB());
        t.addVagon(new Marfa());
        System.out.println(t);
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < vagoane.size(); i++) {
            s.append(vagoane.get(i).toString());
            s.append("\n");
        }

        return s.toString();
    }
}
