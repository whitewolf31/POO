package ex2;

import java.util.StringTokenizer;

public class Problema22 {
    public static void main(String args[]) {
        String s1 = "Marius, de ce nu l-ai ajutat pe George? Era de datoria ta sa vezi ce poti face.";
        String s2 = "ce";
        StringTokenizer st = new StringTokenizer(s1, "?-.,: \n");
        int counter = 0;
        while (st.hasMoreTokens()) {
            String currentToken = st.nextToken();
            if (currentToken.equals(s2)) counter++;
        }
        System.out.println(counter);
    }
}
