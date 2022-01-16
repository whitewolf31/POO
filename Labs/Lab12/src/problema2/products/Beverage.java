package problema2.products;

import problema2.Product;

public class Beverage extends Product {
    public Beverage(double price, String name) {
        super(price, name);
    }

    @Override
    public double getReducedPrice() {
        return price * 0.95;
    }


}
