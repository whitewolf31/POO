package problema2;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.addProduct(ProductFactory.factory("Food", "Salam", 5.34));
        test.addProduct(ProductFactory.factory("Beverage", "Lapte", 2.75));
        test.addProduct(ProductFactory.factory("Computer", "Placa video", 2000.32));
        System.out.println(test.getCheapestProduct());
        test.addProduct(ProductFactory.factory("Book", "Colt Alb", 7.32));
        test.removeProduct(ProductFactory.factory("Beverage", "Lapte", 2.75));
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
