package problema2.products;

import problema2.Product;

public class Computer extends Product {
    public Computer(double price, String name) {
        super(price, name);
    }

    @Override
    public double getReducedPrice() {
        return price * 0.9;
    }
}
