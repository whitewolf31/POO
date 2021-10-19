package ex1;

public class Fractie {
    int numarator;
    int numitor;

    public Fractie(int numarator, int numitor) {
        this.numarator = numarator;
        this.numitor = numitor;
    }

    public Fractie() {
        this(0, 1);
    }

    private int lcm(int a, int b) {
        int c;
        if (b > a) {
            c = b;
            b = a;
            a = c;
        }

        while (b != 0) {
            c = b;
            b = a % b;
            a = c;
        }

        return a;
    }

    public Fractie addFractie(Fractie fractie) {
        Fractie result = new Fractie(0, numitor * fractie.numitor);
        result.numarator = numarator * fractie.numitor + fractie.numarator * numitor;
        int lcm = lcm(result.numarator, result.numitor);
        result.numarator /= lcm;
        result.numitor /= lcm;

        return result;
    }

    @Override
    public String toString() {
        return numarator + "/" + numitor;
    }

    @Override
    public boolean equals(Object tester) {
        if (tester instanceof Fractie) {
            Fractie fractieToTest = (Fractie) tester;
            if (fractieToTest.numarator * numitor == fractieToTest.numitor * numarator) return true;
        }

        return false;
    }

    public static void main(String args[]) {
        Fractie fractie1 = new Fractie(2, 5);
        Fractie fractie2 = new Fractie(4, 10);

        System.out.println(fractie1.equals(fractie2));
    }
}
