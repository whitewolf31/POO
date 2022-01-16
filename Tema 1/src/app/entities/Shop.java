package app.entities;

import app.constants.PotionConsts;
import app.core.CellElement;
import app.player.inventory.Potion;
import app.player.inventory.potion.HealthPotion;
import app.player.inventory.potion.ManaPotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements CellElement {
    private List<Potion> potionList;

    public Shop() {
        Random rand = new Random();
        Integer numOfPotions = rand.nextInt(2) + 2;
        potionList = new ArrayList<Potion>();
        Integer potionType = rand.nextInt(3);
        // Add at least 1 health potion and one mana potion
        if (potionType == 0) potionList.add(new HealthPotion(PotionConsts.LOWCOST, PotionConsts.LOWREGEN, PotionConsts.LOWWEIGHT));
        else if (potionType == 1) potionList.add(new HealthPotion(PotionConsts.MEDCOST, PotionConsts.MEDREGEN, PotionConsts.MEDWEIGHT));
        else potionList.add(new HealthPotion(PotionConsts.HIGHCOST, PotionConsts.HIGHREGEN, PotionConsts.HIGHWEIGHT));
        potionType = rand.nextInt(3);
        if (potionType == 0) potionList.add(new ManaPotion(PotionConsts.LOWCOST, PotionConsts.LOWREGEN, PotionConsts.LOWWEIGHT));
        else if (potionType == 1) potionList.add(new ManaPotion(PotionConsts.MEDCOST, PotionConsts.MEDREGEN, PotionConsts.MEDWEIGHT));
        else potionList.add(new ManaPotion(PotionConsts.HIGHCOST, PotionConsts.HIGHREGEN, PotionConsts.HIGHWEIGHT));

        for (int i = 0; i < numOfPotions - 2; i++) {
            Boolean isHealthPotion = rand.nextBoolean();
            potionType = rand.nextInt(3);

            if (isHealthPotion) {
                if (potionType == 0) potionList.add(new HealthPotion(PotionConsts.LOWCOST, PotionConsts.LOWREGEN, PotionConsts.LOWWEIGHT));
                else if (potionType == 1) potionList.add(new HealthPotion(PotionConsts.MEDCOST, PotionConsts.MEDREGEN, PotionConsts.MEDWEIGHT));
                else potionList.add(new HealthPotion(PotionConsts.HIGHCOST, PotionConsts.HIGHREGEN, PotionConsts.HIGHWEIGHT));
            } else {
                if (potionType == 0) potionList.add(new ManaPotion(PotionConsts.LOWCOST, PotionConsts.LOWREGEN, PotionConsts.LOWWEIGHT));
                else if (potionType == 1) potionList.add(new ManaPotion(PotionConsts.MEDCOST, PotionConsts.MEDREGEN, PotionConsts.MEDWEIGHT));
                else potionList.add(new ManaPotion(PotionConsts.HIGHCOST, PotionConsts.HIGHREGEN, PotionConsts.HIGHWEIGHT));
            }
        }
    }

    public Integer getNumberOfPotions() { return potionList.size(); }

    public Potion getPotionByIndex(Integer idx) { return potionList.get(idx); }

    public List<Potion> getPotionList() { return potionList; }

    @Override
    public String toCharacter() {
        return "S";
    }

    public void buyPotion(Integer potionIndex) {
        potionList.remove((int) potionIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Potion potion: potionList) {
            Integer idx = potionList.indexOf(potion) + 1;
            sb.append(idx).append(". ");
            sb.append(potion);
            if (idx != potionList.size()) sb.append("\n");
        }

        return sb.toString();
    }
}
