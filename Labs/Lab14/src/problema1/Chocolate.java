package problema1;

public class Chocolate extends ToppingDecorator{

    public Chocolate(IceCream iceCream) {
        super(iceCream);
        System.out.println("Adding Chocolate");
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + "chocolate";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 1.5;
    }
}
