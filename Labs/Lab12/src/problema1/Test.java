package problema1;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.addProduct(new Product(5.34, "Salam"));
        test.addProduct(new Product(2.75, "Lapte"));
        test.addProduct(new Product(8.23, "Cascaval"));
        System.out.println(test.getCheapestProduct());
        test.addProduct(new Product(7.23, "Iaurt"));
        test.removeProduct(new Product(2.75, "Lapte"));
        System.out.println(ShopSingleton.getInstance().showProducts());
    }

    public void addProduct(Product product) {
        ShopSingleton.getInstance().getProducts().add(product);
    }

    public void removeProduct(Product product) {
        Product foundProduct = null;
        for (Product loopProduct: ShopSingleton.getInstance().getProducts()) {
            if (loopProduct.equals(product)) {
                foundProduct = loopProduct;
                break;
            }
        }
        if (foundProduct != null) ShopSingleton.getInstance().getProducts().remove(foundProduct);
    }

    public Product getCheapestProduct() {
        Product cheapest = null;
        for (Product product: ShopSingleton.getInstance().getProducts()) {
            if (cheapest == null) cheapest = product;
            else if (cheapest.getPrice() > product.getPrice()) cheapest = product;
        }

        return cheapest;
    }
}
