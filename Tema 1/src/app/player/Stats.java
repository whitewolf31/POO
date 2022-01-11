package app.player;

public class Stats {
    private Integer strength;

    private Integer charisma;

    private Integer dexterity;

    public Stats(Integer strength, Integer charisma, Integer dexterity) {
        this.strength = strength;
        this.charisma = charisma;
        this.dexterity = dexterity;
    }

    public Integer getStrength() { return strength; }

    public Integer getCharisma() { return charisma; }

    public Integer getDexterity() { return dexterity; }

    public Stats setStrength(Integer strength) {
        this.strength = strength;

        return this;
    }

    public Stats setCharisma(Integer charisma) {
        this.charisma = charisma;

        return this;
    }

    public Stats setDexterity(Integer dexterity) {
        this.dexterity = dexterity;

        return this;
    }

    public Stats setAll(Stats newStats) {
        strength = newStats.getStrength();
        charisma = newStats.getCharisma();
        dexterity = newStats.getDexterity();

        return this;
    }
}
