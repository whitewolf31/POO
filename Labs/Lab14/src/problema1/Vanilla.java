package problema1;

public class Vanilla extends ToppingDecorator {

    public Vanilla(IceCream iceCream) {
        super(iceCream);
        System.out.println("Adding Vanilla");
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + " vanilla";
    }

    @Override
    public double getCost() {
        return iceCream.getCost() + 2;
    }
}
