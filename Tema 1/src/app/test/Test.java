package app.test;

import app.Game;

public class Test {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.run();
    }
}
