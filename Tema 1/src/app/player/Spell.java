package app.player;

import app.constants.SpellConstants;
import app.core.visitors.Visitor;
import app.entities.Character;
import app.entities.Enemy;

import java.util.Random;

public abstract class Spell implements Visitor {
    protected Integer damage;

    protected Integer manaRequired;

    public Spell() {
        Random rand = new Random();
        int randNumber = rand.nextInt(3);
        damage = SpellConstants.damageValues[randNumber];
        manaRequired = SpellConstants.manaValues[randNumber];
    }

    public Integer getDamage() { return damage; }

    public Integer getManaRequired() { return manaRequired; }

    public abstract void visit(Character player);

    public abstract void visit(Enemy enemy);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" - Damage: ").append(damage).append(" - Mana: ").append(manaRequired);
        return sb.toString();
    }
}
