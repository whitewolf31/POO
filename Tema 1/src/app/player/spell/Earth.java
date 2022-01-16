package app.player.spell;

import app.entities.Character;
import app.entities.Enemy;
import app.player.Spell;
import app.shared.Logger;

public class Earth extends Spell {
    @Override
    public void visit(Character player) {
        if (!player.getEarthProtection()) player.receiveDamage(damage);
        else Logger.getInstance().logMessage("Your Earth protection saved you!", true);
    }

    @Override
    public void visit(Enemy enemy) {
        if (!enemy.getEarthProtection()) enemy.receiveDamage(damage);
        else Logger.getInstance().logMessage("Enemy is protected against Earth!", true);
    }
}
