package problema4.strategies;

import problema4.Strategy;

public class TwentyStrategy implements Strategy {
    @Override
    public double calcul(int aniVechime, double salariu) {
        return aniVechime / 20.0 * salariu;
    }
}
