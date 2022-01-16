package app.core;

import app.constants.EnemyConsts;
import app.core.visitors.Element;
import app.core.visitors.Visitor;
import app.player.Spell;

import java.util.List;
import java.util.Random;

public abstract class Entity implements Element {
    protected List<Spell> spells;

    protected Integer currentHP;

    protected Integer maxHP;

    protected Integer currentMana;

    protected Integer maxMana;

    protected Boolean fireProtection = false;

    protected Boolean iceProtection = false;

    protected Boolean earthProtection = false;

    public Entity(Integer maxHP, Integer maxMana) {
        this.maxHP = maxHP;
        currentHP = maxHP;
        this.maxMana = maxMana;
        currentMana = maxMana;
    }

    // No params in constructor for enemies
    public Entity() {
        int randNum = new Random().nextInt(3);
        maxHP = EnemyConsts.hpValues[randNum];
        currentHP = maxHP;
        maxMana = EnemyConsts.manaValues[randNum];
        currentMana = maxMana;
    }

    public void regenHP(Integer toAddHP) {
        currentHP += toAddHP;
        if (currentHP > maxHP) currentHP = maxHP;
    }

    public void regenMana(Integer toAddMana) {
        currentMana += toAddMana;
        if (currentMana > maxMana) currentMana = maxMana;
    }

    public Boolean getFireProtection() { return fireProtection; }

    public Boolean getIceProtection() { return iceProtection; }

    public Boolean getEarthProtection() { return earthProtection; }

    public Spell getSpellByIndex(int index) {
        return spells.get(index);
    }

    public Integer getSpellListSize() { return spells.size(); }

    public List<Spell> getSpellList() { return spells; }

    public Integer getCurrentHP() { return currentHP; }

    public Integer getMaxHP() { return maxHP; }

    public Integer getCurrentMana() { return currentMana; }

    public Integer getMaxMana() { return maxMana; }

    public void useSpell(Spell spell, Entity enemy) {
        spells.remove(spell);
        currentMana -= spell.getManaRequired();
        enemy.accept(spell);
    }

    public abstract void receiveDamage(Integer damage);

    public abstract Integer getDamage();

    public abstract void accept(Visitor visitor);
}
