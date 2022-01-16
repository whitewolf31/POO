package problema4;

import problema4.strategies.FortyStrategy;
import problema4.strategies.ThirtyStrategy;
import problema4.strategies.TwentyStrategy;

public class Pensionar {
    Strategy strategy;

    int aniVechime;

    double salariu;

    public Pensionar(int aniVechime, double salariu) {
        this.aniVechime = aniVechime;
        this.salariu = salariu;
        if (aniVechime < 30) strategy = new TwentyStrategy();
        else if (aniVechime < 40) strategy = new ThirtyStrategy();
        else strategy = new FortyStrategy();
    }

    public double getPensie() {
        return strategy.calcul(aniVechime, salariu);
    }
}
