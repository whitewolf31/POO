package ex4;

/**
 * 
 */
import java.util.*;

public class WarriorPack {
    private Vector warriors ;

    public WarriorPack () {
        warriors = new Vector();
    }

    public void addWarrior(Warrior newWarrior) {
        warriors.add(newWarrior);
    }

    public Vector getWarriors() {
        return warriors ;
    }

    public int calculateDamage() {
        int damage = 0;
        for (int i = 0; i < warriors.size(); i++) {
            Warrior warrior = (Warrior) warriors.get(i);
            damage += warrior.getDamage();
        }
        return damage;
    }

    public static void main(String args[]) {
        Snake s1 = new Snake(100, "Snake1");
        Snake s2 = new Snake(150, "Snake2");
        Ogre o1 = new Ogre(500, "Ogre1");
        Ogre o2 = new Ogre(450, "Ogre2");
        Marshmallow m = new Marshmallow(50, "Marshmallow1");

        WarriorPack wp = new WarriorPack();
        wp.addWarrior(s1);
        wp.addWarrior(s2);
        wp.addWarrior(o1);
        wp.addWarrior(o2);
        wp.addWarrior(m);

        System.out.println(s1);
        System.out.println(o1);
        System.out.println(m);
        System.out.println("Total Damage: " + wp.calculateDamage());
    }
}
