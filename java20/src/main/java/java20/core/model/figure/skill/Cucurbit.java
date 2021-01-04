package java20.core.model.figure.skill;

import java20.client.Client;
import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.figure.Creature;
import java20.core.model.figure.King;
import java20.util.GameType;
import java20.util.Race;

/**
 * @author hwd
 * @description 紫金葫芦封印，使1个敌方king单位下1回合不能使用技能
 * @date 2020-12-26
 **/
public class Cucurbit extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Cucurbit(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        Race race = board.getVal(master.getPosition());
        /*
         * 如果是妖精阵营就是封印爷爷的技能
         */
        int result = -1;
        if (race.isCalabash() || race.isGrandpa()) {
            result = controller.displayPickFrame("选择封印对象", new String[]{"蛇精", "蝎子精"}, 300, 100);
        }
        King target = controller.getKing(result + 1);
        target.seal(1);
        this.leftTime = this.cd;
        if (Controller.getInstance().getGameType() == GameType.Playing && Controller.getInstance().isMyTurn()) {
            Client.getInstance().sendMessage("Seal " + target.getPosition().toString());
        }
    }
}
