package java20.core.model.figure;

import java20.core.model.battlefield.Position;
import java20.core.model.figure.movestrategy.MoveStrategy;
import java20.core.model.figure.skill.Skill;
import java20.util.Race;
import java20.util.Status;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author hwd
 * @date 2020-12-26
 **/

@Data
public abstract class Creature {

    private String name;
    private Race race;
    private Position position;
    private Status status;
    private MoveStrategy moveStrategy;

    protected Creature(String name,
                       Race race,
                       Position position,
                       MoveStrategy moveStrategy) {
        this.name = name;
        this.race = race;
        this.position = position;
        this.status = Status.ALIVE;
        this.moveStrategy = moveStrategy;
    }

    public boolean isDead() {
        return this.status.equals(Status.DEAD);
    }

    public boolean isALive() {
        return this.status.equals(Status.ALIVE);
    }

    public boolean isAvailable() {
        return this.status.equals(Status.AVAILABLE);
    }

    public boolean isReachable() {
        return this.status.equals(Status.REACHABLE);
    }

    /**
     * @return 坐标列表
     * @description 获得可以到达的坐标列表
     */
    public ArrayList<Position> getPosList() {
        return this.moveStrategy.availablePos(this.position);
    }
}
