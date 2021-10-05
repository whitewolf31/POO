package ex7;

import java.util.Arrays;

public class Problema7 {
    public Boolean[] ciur(int n) {
        Boolean v[] = new Boolean[n + 1];
        Arrays.fill(v, true);
        for (int i = 2; i <= n / 2; i++) {
            for (int j = 2 * i; j < n; j += i) {
                v[j] = false;
            }
        }

        return v;
    }

    public static void main(String args[]) {
        int n = 24;
        Problema7 problema7 = new Problema7();
        Boolean[] ciur = problema7.ciur(n);
        for (int i = 1; i < n / 2; i++) {
            if (ciur[i] && ciur[n - i]) {
                int b = n - i;
                System.out.println(n + " = " + i + " + " + b);
            }
        }
    }
}
