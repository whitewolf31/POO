package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;

public class Ice extends Spell  {
    @Override
    public void visit(Character player) {
        if (!player.getIceProtection()) player.receiveDamage(damage);
        else System.out.println("Your Ice protection saved you!");
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getIceProtection()) enemy.receiveDamage(damage);
        else System.out.println("Enemy is protected against Ice!");
    }
}
