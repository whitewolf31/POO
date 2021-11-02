package ex4;

public class Snake extends Warrior {
    private final int damage = 10;

    public Snake(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
