package java20.core.model.figure.skill;

import java20.client.Client;
import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Creature;
import java20.util.GameType;
import java20.util.Race;

/**
 * @author hwd
 * @description 喷水，使自身所在直线上的第1个敌方单位封印1回合 封印指无法别己方选中，但可以被敌方选中
 * @date 2020-12-26
 **/
public class Water extends Skill {

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Water(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        Position masterPos = master.getPosition();
        Controller controller = Controller.getInstance();
        Board board = controller.getBoard();
        Race race = board.getVal(masterPos);
        for (int i = 0; i < board.getWidth(); ++i) {
            int y = masterPos.getY() + ((race.isCalabash() || race.isGrandpa()) ? i : -i);
            Position targetPos = new Position(masterPos.getX(), y);
            if (!targetPos.isValid(board.getWidth(), board.getHeight())) {
                break;
            }
            if (!board.isEnemy(targetPos, race)) {
                continue;
            }
            if (targetPos.equals(controller.getUnreachable())) {
                continue;
            }
            Creature target = board.getCreature(targetPos);
            target.seal(1);
            if (Controller.getInstance().getGameType() == GameType.Playing && Controller.getInstance().isMyTurn()) {
                Client.getInstance().sendMessage("Seal " + target.getPosition().toString());
            }
            break;
        }
        this.leftTime = this.cd;
    }
}
