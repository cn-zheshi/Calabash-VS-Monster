package java20.warrior;

import java20.Board;
import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;
import java20.tools.Status;

import java.util.ArrayList;

public abstract class Creature {
    /**
     * one kind of creature have a num as a signal
     */
    KindOfCreature kind;
    MoveStrategy moveStrategy;
    Position p;
    Status status;


    protected Creature(KindOfCreature kindOfCreature, Position p, MoveStrategy strategy) {
        this.kind = kindOfCreature;
        this.p = p;
        this.moveStrategy = strategy;
        this.status = Status.ALIVE;
    }

    public void move(Position position) {
        Board.getBoardInstance().moveTo(p, position, kind);
        this.p = position;
    }

    public void move(int x, int y) {
        move(new Position(x, y));
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDead() {
        return this.status.equals(Status.DEAD);
    }

    public boolean isALive() {
        return this.status.equals(Status.ALIVE);
    }

    public boolean isAvailable() {
        return this.status.equals(Status.AVAILABLE);
    }

    public boolean isReachable() {
        return this.status.equals(Status.REACHABLE);
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
