package app.entities.factories;

import app.entities.Character;
import app.player.type.Mage;
import app.player.type.Rogue;
import app.player.type.Warrior;

public class CharacterFactory {
    public Character createCharacter(String characterType, String name, Integer currentLevel, Integer currentXp) {
        if (characterType == null) return null;
        if (characterType.equals("Warrior")) {
            return new Warrior(name, currentLevel, currentXp);
        }
        if (characterType.equals("Rogue")) {
            return new Rogue(name, currentLevel, currentXp);
        }
        if (characterType.equals("Mage")) {
            return new Mage(name, currentLevel, currentXp);
        }

        return null;
    }
}
