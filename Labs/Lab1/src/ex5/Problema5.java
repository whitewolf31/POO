public class Problema5 {
    public static void main(String args[]) {
        Problema5 problema5 = new Problema5();
        problema5.printNum(2, 4);
    }

    public void printNum(int base, int power) {
        System.out.println(powerCalc(base, power));
        System.out.println(Math.pow(base, power));
    }

    public int powerCalc(int base, int power) {
        if (power > 0) return base * powerCalc(base, power - 1);
        return 1;
    }
}
