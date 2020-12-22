package java20.warrior;

import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public abstract class Creature {
    /**
     * one kind of creature have a num as a signal
     */
    KindOfCreature kind;
    MoveStrategy moveStrategy;
    Position p;
    boolean isDead;

    protected Creature(KindOfCreature kindOfCreature, Position p, MoveStrategy strategy) {
        this.kind = kindOfCreature;
        this.p = p;
        this.moveStrategy = strategy;
        this.isDead = false;
    }

    public void setDead() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
