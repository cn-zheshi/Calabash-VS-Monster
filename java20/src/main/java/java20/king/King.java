package java20.king;

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

    public void setDead() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}
