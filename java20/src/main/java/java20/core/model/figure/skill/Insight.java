package java20.core.model.figure.skill;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.core.model.figure.Goblin;
import java20.core.model.figure.King;
import java20.util.Race;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 洞察，使敌不敢轻举妄动，封印敌方全体技能1回合
 * @date 2020-12-26
 **/
public class Insight extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Insight(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Race race = master.getRace();
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        ArrayList<King> kings = board.getKings();
        ArrayList<Creature> creatures = board.getCreatures();
        for (King cur : kings) {
            if (!board.isEnemy(cur.getPosition(), master.getRace())) {
                continue;
            }
            cur.getSkill().modifyCooldown(1);
        }
        int len = creatures.size();
        for (int i = 0; i < len; ++i) {
            Creature cur = creatures.get(i);
            if (!board.isEnemy(cur.getPosition(), master.getRace())) {
                continue;
            }
            if (i < 7) {
                Calabash calabash = (Calabash) cur;
                calabash.getSkill().modifyCooldown(1);
                continue;
            }
            Goblin goblin = (Goblin) cur;
            if (goblin.getSkill() != null) {
                goblin.getSkill().modifyCooldown(1);
            }
        }
        this.leftTime = this.cd;
    }

}
