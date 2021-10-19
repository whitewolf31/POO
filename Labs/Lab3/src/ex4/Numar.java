package ex4;

public class Numar {
    int nr;

    public Numar(int nr) {
        this.nr = nr;
    }

    public int suma(int a) {
        return a + nr;
    }

    public int suma(int a, int b) {
        return suma(a) + b;
    }

    public int suma(int a, int b, int c) {
        return suma(a, b) + c;
    }

    public int suma(int a, int b, int c, int d) {
        return suma(a, b, c) + d;
    }

    public static void main(String args[]) {
        Numar test = new Numar(2);
        System.out.println(test.suma(2));
        System.out.println(test.suma(2, 3));
        System.out.println(test.suma(2, 3, 4));
        System.out.println(test.suma(2, 3, 4, 5));
    }
}
