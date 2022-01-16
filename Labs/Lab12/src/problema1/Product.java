package problema1;

import java.util.Objects;

public class Product {
    private double price;

    private String name;

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
}
