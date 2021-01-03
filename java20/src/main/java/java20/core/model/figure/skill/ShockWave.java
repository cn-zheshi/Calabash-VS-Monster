package java20.core.model.figure.skill;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Creature;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 冲击波，击杀自身半径1格之外的前后左右4格内的所有敌方单位
 * @date 2020-12-26
 **/
public class ShockWave extends Skill {

    private static int[][] posAround = new int[][] { { 2, 0 }, { -2, 0 }, { 0, 2 }, { 0, -2 } };

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public ShockWave(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Position cur = master.getPosition();
        Board board = Board.getInstance();
        Controller controller = Controller.getInstance();
        ArrayList<Position> available = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            Position tmp = new Position(cur.getX() + posAround[i][0], cur.getY() + posAround[i][1]);
            if (board.isEnemy(tmp, board.getVal(master.getPosition()))) {
                available.add(tmp);
            }
        }
        if (controller.getUnreachable() != null) {
            available.remove(controller.getUnreachable());
        }
        for (Position position : available) {
            Creature creature = board.getCreature(position);
            creature.dead();
        }
        this.leftTime = this.cd;
    }
}
