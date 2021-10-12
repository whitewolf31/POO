package ex1;

public class Problema1 {
    public static void main(String args[]) {
        String s1 = "si";
        String s = "sir1 si cu sir2 fac un sir3";
        int contor = 0;
        int idx = s.indexOf(s1);
        while (idx >= 0) {
            contor++;
            s = s.substring(idx + s1.length());
            idx = s.indexOf(s1);
        }
        System.out.println(contor);
    }
}
