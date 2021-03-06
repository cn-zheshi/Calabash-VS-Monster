package java20.core.model.figure;

import java20.client.Client;
import java20.core.Controller;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.movestrategy.MoveStrategy;
import java20.core.model.figure.skill.Skill;
import java20.util.GameType;
import java20.util.Race;

public class Goblin extends Creature {

    private Skill skill;

    public Goblin(String name, Race race, Position position, MoveStrategy moveStrategy, Skill skill) {
        super(name, race, position, moveStrategy);
        this.skill = skill;
    }

    public void employ() {
        if (this.skill != null) {
            this.skill.employ(this);
            if (Controller.getInstance().getGameType() == GameType.Playing && Controller.getInstance().isMyTurn()) {
                Client.getInstance().sendMessage("UseAbility " + this.getPosition().toString());
            }
        }
    }

    public boolean isSkillAvailable() {
        return this.skill.isAvailable();
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
