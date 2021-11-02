package ex4;

/**
 * 
 */
public abstract class Warrior {
    //0 = dead, 100 = full strength
    public int health;
    public String name;

    public Warrior (int health, String name) {
        this.health = health;
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name + " " + health;
    }

    public abstract int getDamage();
}
