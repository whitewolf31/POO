package ex1;

public class Rectangle extends Shape{
    private double width, length;

    public Rectangle() {
        this(0, 0);
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return width * length;
    }

    public double getPerimeter() {
        return width * 2 + length * 2;
    }

    @Override
    public String toString() {
        return "Rectangle of width = " + width + " and length " + length + ";" + (isFilled() ? " " : " not ") + "filled; and color: " + getColor();
    }
}
