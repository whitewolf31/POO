package ex4;

public class Ogre extends Warrior {
    private final int damage = 5;

    public Ogre(int health, String name) {
        super(health, name);
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
