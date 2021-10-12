package ex2;

public class Problema21 {
    public static void main(String args[]) {
        String s1 = "sir1 si sir2 sunt 2 siruri";
        String s2 = "sir";
        String[] splitStr = s1.split(" ");
        int contor = 0;
        for(String s : splitStr) {
            if (s.equals(s2)) contor++;
        }
        System.out.println(contor);
    }
}
