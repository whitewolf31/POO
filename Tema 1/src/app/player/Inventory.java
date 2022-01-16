package app.player;

import app.player.inventory.Potion;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Potion> potions;

    private Integer maxWeight;

    private Integer coins;

    public Inventory(Integer maxWeight) {
        coins = 0;
        potions = new ArrayList<Potion>();
        this.maxWeight = maxWeight;
    }

    public List<Potion> addPotion(Potion potionToAdd) {
        potions.add(potionToAdd);
        coins -= potionToAdd.getPrice();

        return potions;
    }

    public List<Potion> getPotions() { return potions; }

    public Integer getPotionsSize() { return potions.size(); }

    public Potion selectAndRemovePotion(Integer index) {
        Potion fetchedPotion = potions.get(index);
        potions.remove(index.intValue());

        return fetchedPotion;
    }

    public void giveCoins(Integer coinsToGive) {
        coins += coinsToGive;
    }

    public Integer getRemainingWeight() {
        Integer usedWeight = 0;
        for (Potion potion: potions) {
            usedWeight += potion.getWeight();
        }

        return maxWeight - usedWeight;
    }

    public Integer getCoins() { return coins; }

    public String listPotions() {
        StringBuilder sb = new StringBuilder();
        for (Potion potion: potions) {
            int index = potions.indexOf(potion);
            sb.append(index + 1).append(". ").append(potion.getClass().getSimpleName()).append(" Regen Value: ").append(potion.getRegenValue()).append("\n");
        }
        return sb.toString();
    }
}
