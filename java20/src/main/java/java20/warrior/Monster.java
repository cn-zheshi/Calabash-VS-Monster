package java20.warrior;

import java20.movestrategy.MoveStrategy;
import java20.tools.Position;

public class Monster extends Creature {

    protected Monster(int numOfCreature, Position p, MoveStrategy strategy) {
        super(numOfCreature, p, strategy);
    }
}
