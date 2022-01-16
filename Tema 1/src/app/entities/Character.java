package app.entities;

import app.constants.StatsConsts;
import app.core.Entity;
import app.core.visitors.Visitor;
import app.errors.InsufficientCoinsException;
import app.errors.InsufficientInventoryWeightException;
import app.player.Inventory;
import app.player.Spell;
import app.player.Stats;
import app.player.inventory.Potion;
import app.player.spell.Earth;
import app.player.spell.Fire;
import app.player.spell.Ice;
import app.shared.Coords;
import app.shared.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character extends Entity {
    protected String name;

    private Coords coords;

    protected Inventory inventory;

    protected Integer currentXp;

    protected Integer currentLevel;

    protected Stats stats;

    public Character(String name, Inventory inventory, Integer currentXp, Integer currentLevel, Stats stats, Integer maxHP, Integer maxMana) {
        super(maxHP, maxMana);
        this.coords = new Coords(0, 0);
        this.name = name;
        this.inventory = inventory;
        this.currentLevel = currentLevel;
        this.currentXp = currentXp;
        this.stats = stats;
        Random rand = new Random();
        int randNum = rand.nextInt(3) + 5;
        spells = new ArrayList<Spell>();
        for (int i = 0; i < randNum; i++) {
            int spellKind = rand.nextInt(3);
            if (spellKind == 0) spells.add(new Ice());
            if (spellKind == 1) spells.add(new Fire());
            else spells.add(new Earth());
        }
    }

    public void buyPotion(Potion potionToBuy) throws Exception {
        if (inventory.getRemainingWeight() < potionToBuy.getWeight()) {
            throw new InsufficientInventoryWeightException();
        }
        if (inventory.getCoins() < potionToBuy.getPrice()) {
            throw new InsufficientCoinsException();
        }
        inventory.addPotion(potionToBuy);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() { return name; }

    public Coords getCoords() { return coords; }

    public Inventory getInventory() { return inventory; }

    public Integer getSpellsSize() { return spells.size(); }

    public Integer getCurrentLevel() { return currentLevel; }

    public Integer getCurrentXp() { return currentXp; }

    public void addToCurrentXp(Integer xpToAdd) {
        currentXp += xpToAdd;
    }

    public void setCurrentHP(int value) {
        currentHP = value; // Used for hardcode mode
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append('\n');
        str.append("Current Level: ").append(currentLevel).append('\n');
        str.append("Current XP: ").append(currentXp).append('\n');
        str.append("Type: ").append(getClass().getSimpleName()).append('\n');

        return str.toString();
    }

    @Override
    public void receiveDamage(Integer damage) {
        Integer bonus = Math.floorDiv(stats.getCharisma(), 10) + Math.floorDiv(stats.getDexterity(), 20);
        damage -= bonus;
        if (damage < 0) damage = 0;
        currentHP -= damage;
        if (currentHP < 0) currentHP = 0;

        if (damage == 0) Logger.getInstance().logMessage("You being powerful, received no damage!", true);
        else Logger.getInstance().logMessage("You received " + damage + " damage. HP left: " + currentHP, true);
    }

    @Override
    public Integer getDamage() {
        Integer baseDmg = StatsConsts.baseDmg;
        Integer bonus = Math.floorDiv(stats.getStrength(), 10) + Math.floorDiv(stats.getDexterity(), 20);

        return baseDmg + bonus;
    }

    public String listSpells() {
        StringBuilder sb = new StringBuilder();
        for (Spell spell: spells) {
            int index = spells.indexOf(spell);
            sb.append(index + 1).append(". ").append(spell).append("\n");
        }

        return sb.toString();
    }

    public String showPlayerInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current HP: ").append(currentHP).append("/").append(maxHP).append(", Current Mana: ").append(currentMana).append("/").append(maxMana);
        return sb.toString();
    }
}
