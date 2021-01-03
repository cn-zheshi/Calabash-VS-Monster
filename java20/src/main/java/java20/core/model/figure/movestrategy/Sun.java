package java20.core.model.figure.movestrategy;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 中国象棋马的走法 日字
 * @date 2020-12-26
 **/
public class Sun implements MoveStrategy {

    private static int[][] posAround = new int[][] { { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, -2 }, { -2, -1 },
            { -2, 1 }, { -1, 2 } };

    @Override
    public ArrayList<Position> availablePos(Position position, Race race) {
        int x = position.getX();
        int y = position.getY();
        Controller controller = Controller.getInstance();
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            Position cur = new Position(x + posAround[i][0], y + posAround[i][1]);
            Board board = Board.getInstance();
            if (!cur.isValid(board.getWidth(), board.getHeight()))
                continue;
            if (board.isVoid(cur) || board.isEnemy(cur, race))
                positions.add(cur);
        }
        if (controller.getUnreachable() != null) {
            positions.remove(controller.getUnreachable());
        }
        return positions;
    }

}
