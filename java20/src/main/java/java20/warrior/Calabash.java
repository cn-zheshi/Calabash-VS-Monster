package java20.warrior;

import java20.ablility.Ability;
import java20.movestrategy.MoveStrategy;
import java20.tools.Position;

public class Calabash extends Creature{
    Ability ability;
    public Calabash(int numOfCreature,Position p,MoveStrategy strategy,Ability ability){
        super(numOfCreature,p,strategy);
        this.ability=ability;
    }
}
