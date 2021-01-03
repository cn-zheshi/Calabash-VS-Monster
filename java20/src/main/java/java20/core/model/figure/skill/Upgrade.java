package java20.core.model.figure.skill;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.figure.Creature;
import java20.core.model.figure.Goblin;
import java20.core.model.figure.movestrategy.SlashA;
import java20.util.Race;

/**
 * @author hwd
 * @date 2020-12-27
 **/
public class Upgrade extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Upgrade(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        if (!master.getRace().isMonster()) return;
        if (master.getPosition().getY() > 4) return;
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        master.setMoveStrategy(new SlashA());
        master.setRace(Race.Demon);
        board.setVal(master.getPosition(), master.getRace());
        master.resume();
        ((Goblin) master).setSkill(null);
        this.leftTime = this.cd;
    }
}
