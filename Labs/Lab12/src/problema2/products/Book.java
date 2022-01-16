package problema2.products;

import problema2.Product;

public class Book extends Product {
    public Book(double price, String name) {
        super(price, name);
    }

    @Override
    public double getReducedPrice() {
        return price * 0.85;
    }
}
