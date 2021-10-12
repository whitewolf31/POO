package ex5;

public class Problema5 {
    public static void main (String args[]) {
        String text = "Un terorist avea o bomba";
        String cuvinte[] = new String[2];
        cuvinte[0] = "terorist";
        cuvinte[1] = "bomba";
        System.out.println(Problema5.cenzurare(text, cuvinte));
    }

    public static void suspect(String examinedStr, String[] tests) {
        for (String test : tests) {
            if (examinedStr.contains(test)) {
                System.out.println("Text suspect");
                return;
            }
        }
        System.out.println("Nimic suspect ");
    }

    public static String cenzurare(String examinedStr, String[] tests) {
        for (String test : tests) {
            if (examinedStr.contains(test)) {
                int idx = examinedStr.indexOf(test);
                StringBuilder builder = new StringBuilder(examinedStr);
                while (idx >= 0) {
                    for (int i = idx + 1; i < idx + test.length() - 1; i++) {
                        builder.setCharAt(i, '*');
                    }
                    idx = examinedStr.substring(idx + test.length()).indexOf(test);
                }
                examinedStr = builder.toString();
            }
        }

        return examinedStr;
    }
}
