package java20.warrior;

import java.util.ArrayList;

import java20.Board;
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

    public void move(Position position) {
        Board.getBoardInstance().moveTo(p, position, kind);
        this.p = position;
    }

    public void move(int x, int y) {
        move(new Position(x, y));
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isDead() {
        return isDead;
    }

    public KindOfCreature getKind() {
        return kind;
    }

    public Position getPosition() {
        return p;
    }

    public ArrayList<Position> getPositionsCanBeSet() {
        return moveStrategy.positionsCanBeSet(p.getX(), p.getY());
    }

}
