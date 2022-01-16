package problema2.products;

import problema2.Product;

public class Food extends Product {
    public Food(double price, String name) {
        super(price, name);
    }

    @Override
    public double getReducedPrice() {
        return price * 0.8;
    }
}
