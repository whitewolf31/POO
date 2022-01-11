package app.player.type;

import app.constants.HPConsts;
import app.constants.InventoryConsts;
import app.constants.ManaConsts;
import app.constants.StatsConsts;
import app.entities.Character;
import app.player.Inventory;
import app.player.Stats;

public class Rogue extends Character {
    public Rogue(String name, Integer currentLevel, Integer currentXp) {
        super(
            name,
            new Inventory(InventoryConsts.MED),
            currentXp,
            currentLevel,
            new Stats(currentLevel * 2 + StatsConsts.MED, currentLevel * 2 + StatsConsts.LOW, currentLevel * 2 + StatsConsts.HIGH),
            HPConsts.MED,
            ManaConsts.MED
        );
        super.earthProtection = true;
    }
}
