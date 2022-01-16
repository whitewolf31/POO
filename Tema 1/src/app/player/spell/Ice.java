package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;
import app.shared.Logger;

public class Ice extends Spell  {
    @Override
    public void visit(Character player) {
        if (!player.getIceProtection()) player.receiveDamage(damage);
        else Logger.getInstance().logMessage("Your Ice protection saved you!", true);
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getIceProtection()) enemy.receiveDamage(damage);
        else Logger.getInstance().logMessage("Enemy is protected against Ice!", true);
    }
}
