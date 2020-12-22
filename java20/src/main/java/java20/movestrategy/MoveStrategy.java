package java20.movestrategy;

import java.util.ArrayList;

import java20.tools.Position;

public interface MoveStrategy {
    /**
     * 
     * @param x:生物的横坐标
     * @param y:生物的纵坐标
     * @return 所有可以被移动到的点位
     */
    ArrayList<Position> positionsCanBeSet(int x, int y);
}
