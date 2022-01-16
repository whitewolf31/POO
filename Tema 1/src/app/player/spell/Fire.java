package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;
import app.shared.Logger;

public class Fire extends Spell {
    @Override
    public void visit(Character player) {
        if (!player.getFireProtection()) player.receiveDamage(damage);
        else Logger.getInstance().logMessage("Your Fire protection saved you!", true);
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getFireProtection()) enemy.receiveDamage(damage);
        else Logger.getInstance().logMessage("Enemy is protected against Fire!", true);
    }
}
