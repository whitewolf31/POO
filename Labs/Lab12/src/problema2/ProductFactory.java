package problema2;

import problema2.products.Beverage;
import problema2.products.Book;
import problema2.products.Computer;
import problema2.products.Food;

public class ProductFactory {
    private ProductFactory() {}

    public static Product factory(String type, String nameProduct, double priceProduct) {
        if (type.equals("Book")) return new Book(priceProduct, nameProduct);
        if (type.equals("Beverage")) return new Beverage(priceProduct, nameProduct);
        if (type.equals("Computer")) return new Computer(priceProduct, nameProduct);
        if (type.equals("Food")) return new Food(priceProduct, nameProduct);
        return null;
    }
}
