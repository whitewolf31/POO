package app.player.inventory.potion;

import app.entities.Character;
import app.player.inventory.Potion;

public class ManaPotion implements Potion {
    private Integer price;

    private Integer regenValue;

    private Integer weight;

    public ManaPotion(Integer price, Integer regenValue, Integer weight) {
        this.price = price;
        this.regenValue = regenValue;
        this.weight = weight;
    }

    @Override
    public void usePotion(Character player) {
        player.regenMana(regenValue);
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public Integer getRegenValue() {
        return regenValue;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mana Potion (Mana: ").append(regenValue).append(", Weight: ").append(weight).append(") - Cost: ").append(price);

        return sb.toString();
    }
}
