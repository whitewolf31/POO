package ex4;

public class Marshmallow extends Warrior {
    private final int damage = 1;

    public Marshmallow(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
