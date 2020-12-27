package java20.core.model.figure.skill;


import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Creature;
import java20.util.Race;

/**
 * @author hwd
 * @description 喷火，击杀自己所在直线上的第一个敌方单位，己方免疫
 * @date 2020-12-26
 **/
public class Flame extends Skill {


    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Flame(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Position masterPos = master.getPosition();
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        Race race = master.getRace();
        for (int i = 0; i < board.getWidth(); ++i) {
            int x = masterPos.getX() + ((race.isCalabash() || race.isGrandpa()) ? 1 : -1);
            Position targetPos = new Position(x, masterPos.getY());
            if (!targetPos.isValid(board.getWidth(), board.getHeight())) break;
            if (!board.isEnemy(targetPos, race)) continue;
            Creature target = board.getCreature(targetPos);
            target.dead();
            break;
        }
        this.leftTime = this.cd;
    }

}
