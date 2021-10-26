package ex1;

public class Patrulater {
    public int latura1, latura2, latura3, latura4;
    public double unghi1, unghi2, unghi3, unghi4;

    public Patrulater() {
        this(0, 0, 0, 0);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4) {
        this.latura1 = latura1;
        this.latura2 = latura2;
        this.latura3 = latura3;
        this.latura4 = latura4;
    }

    public Patrulater(double unghi1, double unghi2, double unghi3, double unghi4) {
        this(0, 0, 0, 0, unghi1, unghi2, unghi3, unghi4);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4,
                      double unghi1, double unghi2, double unghi3, double unghi4) {
        this(latura1, latura2, latura3, latura4);
        this.unghi1 = unghi1;
        this.unghi2 = unghi2;
        this.unghi3 = unghi3;
        this.unghi4 = unghi4;
    }

    public int perimetru() {
        int result;
        result = latura1 + latura2 + latura3 + latura4;
        return result;
    }

    public static void main(String args[]) {
        Paralelogram plg = new Paralelogram(4, 2, 30);
        Dreptunghi d = new Dreptunghi(5, 3);
        Romb r = new Romb(4, 30);
        Patrat p = new Patrat(10);
        System.out.println("Arie ex1.Paralelogram " + plg.arie());
        System.out.println("Arie ex1.Dreptunghi " + d.arie());
        System.out.println("Arie ex1.Romb " + r.arie());
        System.out.println("Arie ex1.Patrat " + p.arie());
    }
}

class Paralelogram extends Patrulater {

    public Paralelogram() {}

    public Paralelogram(int latura1, int latura2, int unghi) {
        super(latura1, latura2, latura1, latura2, unghi, 180 - unghi, unghi, 180 - unghi);
    }

    public double arie() {
        return latura1 * latura2 * Math.sin(unghi1 * Math.PI / 180);
    }

}

class Dreptunghi extends Paralelogram {

    public Dreptunghi() {}

    public Dreptunghi(int latura1, int latura2) {
        super(latura1, latura2, 90);
    }

    public double arie() {
        return latura1 * latura2;
    }

}

class Romb extends Paralelogram {

    private double diagonala1, diagonala2;

    public Romb() {}

    public Romb(int latura, int unghi) {
        super(latura, latura, unghi);
        this.diagonala1 = latura / Math.sin(unghi * Math.PI / 360);
        this.diagonala2 = latura / Math.cos(unghi * Math.PI / 360);
    }

    public double arie() {
        return (diagonala2 * diagonala1) / 2;
    }
}

class Patrat extends Dreptunghi {

    public Patrat() {}

    public Patrat(int latura) {
        super(latura, latura);
    }

    public double arie() {
        return super.arie();
    }
}