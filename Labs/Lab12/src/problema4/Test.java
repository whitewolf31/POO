package problema4;

public class Test {
    public static void main(String[] args) {
        Pensionar p1 = new Pensionar(15, 2300.0);
        Pensionar p2 = new Pensionar(33, 4321.12);
        Pensionar p3 = new Pensionar(50, 7432.0);
        System.out.println(p1.getPensie());
        System.out.println(p2.getPensie());
        System.out.println(p3.getPensie());
    }
}
