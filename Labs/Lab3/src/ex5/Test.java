package ex5;

public class Test {
    public static void main(String args[]) {
        Point p1 = new Point();
        p1.setX(1);
        p1.setY(2);
        Point p2 = new Point();
        p2.setX(-1);
        p2.setY(3);
        System.out.println(p1.distance(p2));
    }
}
