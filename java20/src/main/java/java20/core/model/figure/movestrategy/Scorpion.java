package java20.core.model.figure.movestrategy;

import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 周围8格
 * @date 2020-12-26
 **/
public class Scorpion implements MoveStrategy {

    private static int[][] posAround = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
            { 1, 0 }, { 1, 1 } };

    @Override
    public ArrayList<Position> availablePos(Position position) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            Position cur = new Position(x + posAround[i][0], y + posAround[i][1]);
            Board board = Board.getInstance();
            if (!cur.isValid(board.getWidth(), board.getHeight()))
                continue;
            if (board.isVoid(cur) || board.isEnemy(cur, Race.Scorpion))
                positions.add(cur);
        }
        return positions;
    }

}
