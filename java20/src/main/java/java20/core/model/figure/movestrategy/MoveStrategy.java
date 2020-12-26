package java20.core.model.figure.movestrategy;

import java.util.ArrayList;

import java20.core.model.battlefield.Position;

public interface MoveStrategy {
    /**
     * @param x:生物的横坐标
     * @param y:生物的纵坐标
     * @return 所有可以被移动到的点位
     */
    public ArrayList<Position> availablePos(Position position);
}
