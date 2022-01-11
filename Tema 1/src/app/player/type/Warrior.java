package app.player.type;

import app.constants.HPConsts;
import app.constants.InventoryConsts;
import app.constants.ManaConsts;
import app.constants.StatsConsts;
import app.entities.Character;
import app.player.Inventory;
import app.player.Stats;

public class Warrior extends Character {

    public Warrior(String name, Integer currentLevel, Integer currentXp) {
        super(
            name,
            new Inventory(InventoryConsts.HIGH),
            currentXp, currentLevel,
            new Stats(currentLevel * 2 + StatsConsts.HIGH, currentLevel * 2 + StatsConsts.MED, currentLevel * 2 + StatsConsts.LOW),
            HPConsts.HIGH,
            ManaConsts.LOW
        );
        super.fireProtection = true;
    }
}
