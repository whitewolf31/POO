package app.player.inventory.potion;

import app.entities.Character;
import app.player.inventory.Potion;
import app.shared.Logger;

import java.awt.*;

public class HealthPotion implements Potion {
    private Integer price;

    private Integer regenValue;

    private Integer weight;

    public HealthPotion(Integer price, Integer regenValue, Integer weight) {
        this.price = price;
        this.regenValue = regenValue;
        this.weight = weight;
    }

    @Override
    public void usePotion(Character player) {
        player.regenHP(regenValue);
        Logger.getInstance().logMessage("You have healed for " + regenValue + " HP points", true, Color.GREEN);
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
        sb.append("Health Potion (HP: ").append(regenValue).append(", Weight: ").append(weight).append(") - Cost: ").append(price);

        return sb.toString();
    }
}
