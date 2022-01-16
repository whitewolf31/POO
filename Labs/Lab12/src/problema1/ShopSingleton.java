package problema1;

import java.util.ArrayList;
import java.util.List;

public class ShopSingleton {
    private String name;

    private List<Product> products;

    private static ShopSingleton instance;

    private ShopSingleton() {
        products = new ArrayList<Product>();
    }

    public static ShopSingleton getInstance() {
        if (instance == null) instance = new ShopSingleton();

        return instance;
    }

    public String showProducts() {
        StringBuilder sb = new StringBuilder();
        for (Product product: products) {
            int index = products.indexOf(product);
            sb.append(index + 1).append(". ").append(product.getName()).append(", Price: ").append(product.getPrice()).append("\n");
        }

        return sb.toString();
    }

    public List<Product> getProducts() { return products; }
}
