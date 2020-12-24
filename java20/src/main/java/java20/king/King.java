package java20.king;

import java.util.ArrayList;

import java20.Board;
import java20.ablility.*;
import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public class King {
    /**
     * one kind of creature have a num as a signal
     */
    KindOfCreature kind;
    Ability ability;
    MoveStrategy moveStrategy;
    Position p;
    boolean isDead;

    public King(KindOfCreature kindOfCreature, Position p, MoveStrategy strategy, Ability ability) {
        this.ability = ability;
        this.moveStrategy = strategy;
        this.kind = kindOfCreature;
        this.p = p;
        this.isDead = false;
    }

    public boolean canUseAbility() {
        return ability.canDoSth();
    }

    public void useAbility() {
        ability.doSth();
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
