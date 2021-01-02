package java20.core.model.figure.skill;

import java20.client.Client;
import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.util.GameType;
import java20.util.Rand;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 召唤，在自身周围随机8格内召唤1个自定义葫芦娃，死去的葫芦娃会复活，活着的葫芦娃会传送
 * @date 2020-12-26
 **/

public class Call extends Skill {

    private static int[][] posAround = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
            { 1, 0 }, { 1, 1 } };

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Call(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Position cur = master.getPosition();
        Board board = Board.getInstance();
        Controller controller = Controller.getInstance();
        ArrayList<Position> available = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            Position tmp = new Position(cur.getX() + posAround[i][0], cur.getY() + posAround[i][1]);
            if (board.isVoid(tmp))
                available.add(tmp);
        }
        int rand = Rand.randNum(available.size());
        int result = controller.displayPickFrame("选择召唤对象", new String[] { "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃" },
                300, 100);
        Calabash target = controller.getCalabash(result);
        if (target.isDead()) {
            target.resurge();
            board.setVal(available.get(rand), target.getRace());
            if (Controller.getInstance().getGameType() == GameType.Playing && Controller.getInstance().isMyTurn()) {
                Client.getInstance().sendMessage("Set " + result + " " + available.get(rand).toString());
            }
        } else {
            board.moveTo(target.getPosition(), available.get(rand), target.getRace());
        }
        target.setPosition(available.get(rand));
        this.leftTime = this.cd;
    }
}
