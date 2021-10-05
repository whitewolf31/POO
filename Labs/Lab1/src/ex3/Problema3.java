public class Problema3 {
    public static void main(String args[]) {
        Problema3Extend obiect = new Problema3Extend();
        obiect.print("Hello World");
    }
}

class Problema3Extend {
    public void print(String str) {
        System.out.println(str);
    }
}
