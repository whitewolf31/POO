package app.core.visitors;

import app.entities.Character;
import app.entities.Enemy;

public interface Visitor {
    public void visit(Character player);

    public void visit(Enemy enemy);
}
