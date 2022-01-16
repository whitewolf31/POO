package problema4.strategies;

import problema4.Strategy;

public class FortyStrategy implements Strategy {
    @Override
    public double calcul(int aniVechime, double salariu) {
        return aniVechime / 40.0 * salariu;
    }
}
