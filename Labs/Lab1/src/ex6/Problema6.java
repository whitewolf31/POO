package ex6;

public class Problema6 {
    public static void main(String args[]) {
        Problema6 primeCalc = new Problema6();
        for (int i = 1; i <= 20; i++) {
            if (primeCalc.isPrime(i)) System.out.println(i + " is prime");
            else System.out.println(i + " is not prime");
        }
    }

    public Boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;

        return true;
    }
}
