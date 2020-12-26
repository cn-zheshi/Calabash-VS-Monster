package java20.core.model.figure.skill;


import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Creature;
import java20.core.model.figure.movestrategy.MoveStrategy;
import java20.core.model.figure.movestrategy.Periphery;
import java20.util.Rand;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 召唤，在自身周围随机8格内召唤1个自定义葫芦娃，死去的葫芦娃会复活，活着的葫芦娃会传送
 * @date 2020-12-26
 **/

public class Call implements Skill {

    private static int[][] posAround = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    @Override
    public void employ(Creature master) {
        Position cur = master.getPosition();
        Board board = Board.getInstance();
        ArrayList<Position> available = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            Position tmp = new Position(cur.getX() + posAround[i][0],
                    cur.getY() + posAround[i][1]);
            if (board.isVoid(tmp)) available.add(tmp);
        }
        int rand = Rand.randNum(available.size());
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
