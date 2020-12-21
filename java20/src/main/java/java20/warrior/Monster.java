package java20.warrior;

import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public class Monster extends Creature {

    protected Monster(KindOfCreature kindOfCreature, Position p, MoveStrategy strategy) {
        super(kindOfCreature, p, strategy);
    }
}
