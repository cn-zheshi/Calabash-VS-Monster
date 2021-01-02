package java20.core.model.figure.skill;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.util.Race;

/**
 * @author hwd
 * @description 蛊惑，控制1个葫芦娃的移动控制权和技能控制权，但葫芦娃攻击目标不能是爷爷
 * @date 2020-12-26
 **/
public class Seduction extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Seduction(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Position cur = master.getPosition();
        Board board = Board.getInstance();
        Controller controller = Controller.getInstance();
        int result = controller.displayPickFrame("选择魅惑对象", new String[] { "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃" },
                300, 100);
        Calabash target = controller.getCalabash(result);
        board.setVal(target.getPosition(), Race.Goblin);
        target.betray(1);
        target.getSkill().employ(master);
        this.leftTime = this.cd;
    }

}
