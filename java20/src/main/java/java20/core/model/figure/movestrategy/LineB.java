package java20.core.model.figure.movestrategy;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

import java.util.ArrayList;

/**
 * @description 前后左右各2格
 **/
public class LineB implements MoveStrategy {

    @Override
    public ArrayList<Position> availablePos(Position position, Race race) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Position> arr = new ArrayList<Position>();
        Controller controller = Controller.getInstance();
        if (Board.getInstance().isVoid(x + 2, y) || Board.getInstance().isEnemy(x + 2, y, race)) {
            Position cur = new Position(x + 2, y);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight())) {
                arr.add(cur);
            }
        }
        if (Board.getInstance().isVoid(x - 2, y) || Board.getInstance().isEnemy(x - 2, y, race)) {
            Position cur = new Position(x - 2, y);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight())) {
                arr.add(cur);
            }
        }
        if (Board.getInstance().isVoid(x, y + 2) || Board.getInstance().isEnemy(x, y + 2, race)) {
            Position cur = new Position(x, y + 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight())) {
                arr.add(cur);
            }
        }
        if (Board.getInstance().isVoid(x, y - 2) || Board.getInstance().isEnemy(x, y - 2, race)) {
            Position cur = new Position(x, y - 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight())) {
                arr.add(cur);
            }
        }
        if(controller.getUnreachable()!=null){
            arr.remove(controller.getUnreachable());
        }
        return arr;
    }

}
