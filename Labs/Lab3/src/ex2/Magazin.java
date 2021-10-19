package ex2;

public class Magazin {
    String name;
    Produs produs1;
    Produs produs2;
    Produs produs3;

    public Magazin(String name, Produs p1, Produs p2, Produs p3) {
        this.name = name;
        produs1 = p1;
        produs2 = p2;
        produs3 = p3;
    }

    @Override
    public String toString() {
        return produs1.toString() + "\n" + produs2.toString() + "\n" + produs3.toString();
    }

    public double getTotalMagazin() {
        return produs1.getTotalProdus() + produs2.getTotalProdus() + produs3.getTotalProdus();
    }

    public static void main(String args[]) {
        Magazin magazin = new Magazin("Mega Image", new Produs("Cola", 2.5, 2), new Produs("Fanta", 3, 1), new Produs("Schweppes", 1.2, 4));
        System.out.println(magazin);
        System.out.println(magazin.getTotalMagazin());
    }
}
