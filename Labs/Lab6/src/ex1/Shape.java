package ex1;

/**
 *
 * @author Nan Mihai
 */
public abstract class Shape {
    private String color;
    private boolean filled;

    public Shape() {
        this("red", true);
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
    public abstract String toString();

    public static void main(String[] args) {
        Circle c = new Circle(3, "red", true);
        Rectangle r = new Rectangle(4, 2, "blue", false);
        Square s = new Square(5, "green", true);
        System.out.println(c);
        System.out.println(r);
        System.out.println(s.getArea());

        // Problema 2
        // Rectangle rTest1 = new Shape("Blue", false) // Nu merge
        Rectangle rTest2 = new Square(5); // Merge
        Square sTest = new Square(3);
        Rectangle rectangle;
        Circle circle;
        Shape shape;
        if (sTest instanceof Rectangle) {
            System.out.println("Rectangle Works!");
            rectangle = (Rectangle) sTest;
        }
        // NU MERGE
//        if (sTest instanceof Circle) {
//            System.out.println("Circle Works!");
//            circle = (Circle) sTest
//        }
        if (sTest instanceof Shape) {
            System.out.println("Shape Works!");
            shape = (Shape) sTest;
        }

        // Parte 2 problema 2
//        //Conversie 1
//        Circle c1 = new Circle();
//        Square sq = (Square) c1; // Nu merge
//        //Conversie 2
//        Rectangle rect = new Rectangle(5.0, 5.0);
//        sq = (Square) rect; // Nu merge
//       //Conversie 3
//        sq = new Square(7.0);
//        r = sq;  // Merge
    }
}
