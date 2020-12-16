package java20.warrior;

import java20.movestrategy.MoveStrategy;
import java20.tools.Position;

public abstract class Creature {
    /**
     * one kind of creature have a num as a signal
     */
    int num;
    MoveStrategy moveStrategy;
    Position p;
    protected Creature(int numOfCreature,Position p,MoveStrategy strategy){
        this.num=numOfCreature;
        this.p=p;
        this.moveStrategy=strategy;
    }
}
