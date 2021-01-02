package java20.core.model.figure.skill;

import java20.client.Client;
import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.util.GameType;

/**
 * @author hwd
 * @description 弱者退散，使1个葫芦娃后退1格并封印能力1回合
 * @date 2020-12-26
 **/
public class Fading extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Fading(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        int result = controller.displayPickFrame("选择葫芦娃", new String[] { "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃" },
                300, 100);
        Calabash target = controller.getCalabash(result);
        target.seal(1);
        Position cur = target.getPosition();
        Position destination = new Position(cur);
        if (destination.getY() != 0)
            destination.setY(destination.getY() - 1);
        board.moveTo(cur, destination, target.getRace());
        target.setPosition(destination);
        this.leftTime = this.cd;
        if (Controller.getInstance().getGameType() == GameType.Playing && Controller.getInstance().isMyTurn()) {
            Client.getInstance().sendMessage("Seal " + target.getPosition().toString());
        }
    }
}
