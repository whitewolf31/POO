package app.player.type;

import app.constants.HPConsts;
import app.constants.InventoryConsts;
import app.constants.ManaConsts;
import app.constants.StatsConsts;
import app.entities.Character;
import app.player.Inventory;
import app.player.Stats;

public class Mage extends Character {

    public Mage(String name, Integer currentLevel, Integer currentXp) {
        super(
            name,
            new Inventory(InventoryConsts.LOW),
            currentXp,
            currentLevel,
            new Stats(currentLevel * 2 + StatsConsts.LOW, currentLevel * 2 + StatsConsts.HIGH, currentLevel * 2 + StatsConsts.MED),
            HPConsts.LOW,
            ManaConsts.HIGH
        );
        super.iceProtection = true;
    }
}
