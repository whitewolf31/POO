package app.player.inventory;

import app.entities.Character;

public interface Potion {
    public void usePotion(Character player);

    public Integer getPrice();

    public Integer getRegenValue();

    public Integer getWeight();
}
