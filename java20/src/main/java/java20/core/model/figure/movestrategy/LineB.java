package java20.core.model.figure.movestrategy;

import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

import java.util.ArrayList;

/**
 * @description 前后左右各2格
 **/
public class LineB implements MoveStrategy {

    @Override
    public ArrayList<Position> availablePos(Position position) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Position> arr = new ArrayList<Position>();
        if (Board.getInstance().isVoid(x + 2, y + 2)
                || Board.getInstance().isEnemy(x + 2, y + 2, Race.Calabash)) {
            Position cur = new Position(x + 2, y + 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getInstance().isVoid(x + 2, y - 2)
                || Board.getInstance().isEnemy(x + 2, y - 2, Race.Calabash)) {
            Position cur = new Position(x + 2, y - 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getInstance().isVoid(x - 2, y + 2)
                || Board.getInstance().isEnemy(x - 2, y + 2, Race.Calabash)) {
            Position cur = new Position(x - 2, y + 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getInstance().isVoid(x - 2, y - 2)
                || Board.getInstance().isEnemy(x - 2, y - 2, Race.Calabash)) {
            Position cur = new Position(x - 2, y - 2);
            if (cur.isValid(Board.getInstance().getWidth(), Board.getInstance().getHeight()))
                arr.add(cur);
        }
        return arr;
    }

}
