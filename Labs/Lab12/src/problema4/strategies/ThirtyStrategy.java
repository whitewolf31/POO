package problema4.strategies;

import problema4.Strategy;

public class ThirtyStrategy implements Strategy {
    @Override
    public double calcul(int aniVechime, double salariu) {
        return aniVechime / 30.0 * salariu;
    }
}
