package java20.core.model.figure.movestrategy;

import java.util.ArrayList;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

/**
 * @description 直线
 **/
public class LineA implements MoveStrategy {

    @Override
    public ArrayList<Position> availablePos(Position position, Race race) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Position> arr = new ArrayList<>();
        boolean left = true;
        boolean right = true;
        boolean up = true;
        boolean down = true;
        Controller controller = Controller.getInstance();
        for (int i = 1; i < Board.getInstance().getWidth(); ++i) {
            if (right && Board.getInstance().isVoid(x + i, y)) {
                arr.add(new Position(x + i, y));
            } else if (right) {
                if (Board.getInstance().isEnemy(x + i, y, race)) {
                    arr.add(new Position(x + i, y));
                }
                right = false;
            }
            if (left && Board.getInstance().isVoid(x - i, y)) {
                arr.add(new Position(x - i, y));
            } else if (left) {
                if (Board.getInstance().isEnemy(x - i, y, race)) {
                    arr.add(new Position(x - i, y));
                }
                left = false;
            }
            if (!left && !right) {
                break;
            }
        }
        for (int i = 1; i < Board.getInstance().getHeight(); ++i) {
            if (down && Board.getInstance().isVoid(x, y + i)) {
                arr.add(new Position(x, y + i));
            } else if (down) {
                if (Board.getInstance().isEnemy(x, y + i, race)) {
                    arr.add(new Position(x, y + i));
                }
                down = false;
            }
            if (up && Board.getInstance().isVoid(x, y - i)) {
                arr.add(new Position(x, y - i));
            } else if (up) {
                if (Board.getInstance().isEnemy(x, y - i, race)) {
                    arr.add(new Position(x, y - i));
                }
                up = false;
            }
            if (!up && !down) {
                break;
            }
        }
        if (controller.getUnreachable() != null) {
            arr.remove(controller.getUnreachable());
        }
        return arr;
    }

}
