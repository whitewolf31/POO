package problema2;

import java.util.Objects;

public abstract class Product {
    protected double price;

    protected String name;

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() { return price; }

    public String getName() { return name; }

    @Override
    public String toString() { return name + " " + price; }

    public boolean equals(Product otherProduct) {
        return Objects.equals(name, otherProduct.getName()) && price == otherProduct.getPrice();
    }

    public abstract double getReducedPrice();
}
