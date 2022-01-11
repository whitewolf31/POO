package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;

public class Fire extends Spell {
    @Override
    public void visit(Character player) {
        if (!player.getFireProtection()) player.receiveDamage(damage);
        else System.out.println("Your Fire protection saved you!");
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getFireProtection()) enemy.receiveDamage(damage);
        else System.out.println("Enemy is protected against Fire!");
    }
}
