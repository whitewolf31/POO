package ex1;

public class StormFire extends BadLuck{

    @Override
    void execute(Hero h) {
        System.out.println(h);
    }

    @Override
    void execute(Warrior w) {
        System.out.println(w);
    }

    @Override
    void execute(Ninja n) {
        System.out.println(n);
    }

    @Override
    void execute(Rogue r) {
        System.out.println(r);
    }
}
