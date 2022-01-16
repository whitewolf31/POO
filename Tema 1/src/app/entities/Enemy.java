package app.entities;

import app.constants.StatsConsts;
import app.core.CellElement;
import app.core.Entity;
import app.core.visitors.Visitor;
import app.player.Spell;
import app.player.spell.Earth;
import app.player.spell.Fire;
import app.player.spell.Ice;
import app.shared.Logger;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity implements CellElement {

    public Enemy() {
        super();
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        if (randNum == 0) fireProtection = true;
        if (randNum == 1) iceProtection = true;
        else earthProtection = true;
        randNum = rand.nextInt(3) + 2;
        spells = new ArrayList<Spell>();
        for (int i = 0; i < randNum; i++) {
            int spellKind = rand.nextInt(3);
            if (spellKind == 0) spells.add(new Ice());
            if (spellKind == 1) spells.add(new Fire());
            else spells.add(new Earth());
        }
    }

    @Override
    public String toCharacter() {
        return "E";
    }

    @Override
    public void receiveDamage(Integer damage) {
        // Eliminated 50% chance to duck since it's way too op
        currentHP -= damage;
        if (currentHP < 0) currentHP = 0;
        Logger.getInstance().logMessage("Enemy has received " + damage + " damage points. HP left: " + currentHP, true);
    }

    @Override
    public Integer getDamage() {
        int baseDmg = StatsConsts.baseDmg;
        Boolean rand = new Random().nextBoolean();

        return rand ? baseDmg * 2 : baseDmg;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
