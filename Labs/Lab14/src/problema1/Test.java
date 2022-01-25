package problema1;

public class Test {
    public static void main(String[] args) {
        IceCream basicIceCream = new BasicIceCream();
        IceCream chocolateIceCream = new Chocolate(basicIceCream);
        IceCream chocoAndVanillaIceCream = new Vanilla(chocolateIceCream);
        System.out.println(chocoAndVanillaIceCream.getDescription());
        System.out.println("Cost: " + chocoAndVanillaIceCream.getCost());
    }
}
