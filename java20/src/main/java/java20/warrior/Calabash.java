package java20.warrior;

import java20.ablility.Ability;
import java20.movestrategy.MoveStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public class Calabash extends Creature {
    Ability ability;

    public Calabash(KindOfCreature kindOfCreature, Position p, MoveStrategy strategy, Ability ability) {
        super(kindOfCreature, p, strategy);
        this.ability = ability;
    }

    public boolean canUseAbility() {
        return ability.canDoSth();
    }

    public void useAbility() {
        ability.doSth();
    }

}
