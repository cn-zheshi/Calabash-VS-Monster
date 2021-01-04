package java20.core.model.figure;

import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.movestrategy.MoveStrategy;
import java20.util.Race;
import java20.util.Status;

import java.util.ArrayList;

public abstract class Creature {

    protected String name;
    protected Race race;
    protected Position position;
    protected Status status;
    protected MoveStrategy moveStrategy;
    /*
     * statusTime表示状态持续时间 -2为死亡 0表示别的状态时间结束需要返回到alive状态 alive状态下这个数值为0 时间永远是2的倍数
     * 因为两个turn才是一个回合
     */
    protected int statusTime;

    protected Creature(String name, Race race, Position position, MoveStrategy moveStrategy) {
        this.name = name;
        this.race = race;
        this.position = position;
        this.status = Status.ALIVE;
        this.moveStrategy = moveStrategy;
        this.statusTime = 0;
    }

    public void resume() {
        this.statusTime = 0;
        this.status = Status.ALIVE;
    }

    public void resurge() {
        if (!this.status.equals(Status.DEAD))
            return;
        this.statusTime = 0;
        this.status = Status.ALIVE;
    }

    public void dead() {
        if (this.status.equals(Status.INVINCIBLE))
            return;
        this.statusTime = -2;
        this.status = Status.DEAD;
        Board.getInstance().setVal(this.position, null);
    }

    public void seal(int turns) {
        this.statusTime = turns * 2;
        this.status = Status.REACHABLE;
    }

    public void hide(int turns) {
        this.statusTime = turns * 2;
        this.status = Status.AVAILABLE;
    }

    public void intensify(int turns) {
        this.statusTime = turns * 2;
        this.status = Status.INVINCIBLE;
    }

    public void betray(int turns) {
        this.statusTime = turns;
        this.status = Status.TRAITOROUS;
    }

    public void updateStatus() {
        Board board = Board.getInstance();
        if (this.status.equals(Status.ALIVE) || this.status.equals(Status.DEAD)) {
            return;
        }
        --this.statusTime;
        if (this.statusTime == 0) {
            if (this.isTraitorous()) {
                board.setVal(this.position, this.race);
            }
            this.status = Status.ALIVE;
            return;
        }
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

    public boolean isInvincible() {
        return this.status.equals(Status.INVINCIBLE);
    }

    public boolean isTraitorous() {
        return this.status.equals(Status.TRAITOROUS);
    }

    public void move(int x, int y) {
        move(new Position(x, y));
    }

    public void move(Position position) {
        Board.getInstance().moveTo(this.position, position, Board.getInstance().getVal(this.position));
    }

    /**
     * @return 坐标列表
     * @description 获得可以到达的坐标列表
     */
    public ArrayList<Position> getPosList() {
        return this.moveStrategy.availablePos(this.position, Board.getInstance().getVal(position));
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public int getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(int statusTime) {
        this.statusTime = statusTime;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
