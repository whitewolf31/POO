package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;

public class Earth extends Spell {
    @Override
    public void visit(Character player) {
        if (!player.getEarthProtection()) player.receiveDamage(damage);
        else System.out.println("Your Earth protection saved you!");
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getEarthProtection()) enemy.receiveDamage(damage);
        else System.out.println("Enemy is protected against Earth!");
    }
}
