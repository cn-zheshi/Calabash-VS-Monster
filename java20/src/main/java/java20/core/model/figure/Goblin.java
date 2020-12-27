package java20.core.model.figure;

import java20.core.model.battlefield.Position;
import java20.core.model.figure.movestrategy.MoveStrategy;
import java20.core.model.figure.skill.Skill;
import java20.util.Race;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hwd
 * @date 2020-12-27
 **/
@Getter
@Setter
public class Goblin extends Creature {

    private Skill skill;

    public Goblin(String name, Race race, Position position, MoveStrategy moveStrategy, Skill skill) {
        super(name, race, position, moveStrategy);
        this.skill = skill;
    }

    public void employ() {
        if (this.skill != null)
            this.skill.employ(this);
    }

    public boolean isAvailable() {
        return this.skill.isAvailable();
    }
}
