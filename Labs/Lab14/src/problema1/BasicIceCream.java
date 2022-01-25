package problema1;

public class BasicIceCream implements IceCream{

    public BasicIceCream() {
        System.out.println("Adding cone");
    }

    @Override
    public String getDescription() {
        return "Ingrediente: con ";
    }

    @Override
    public double getCost() {
        return 0.5;
    }
}
