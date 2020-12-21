package java20.king;

import java20.ablility.*;
import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public abstract class King {
    Ability ability;
    MoveStrategy moveStrategy;
    KindOfCreature kindOfCreature;
    Position p;
    protected King(Ability ability,MoveStrategy strategy,KindOfCreature kindOfCreature,Position p){
        this.ability=ability;
        this.moveStrategy=strategy;
        this.kindOfCreature=kindOfCreature;
        this.p=p;
    }
    public boolean canUseAbility(){
        return ability.canDoSth();
    }
    abstract public void useAbility();
}
