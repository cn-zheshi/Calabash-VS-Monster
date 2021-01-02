package java20.core.model.figure.movestrategy;

import java20.core.model.battlefield.Board;
import java20.util.Race;
import java20.core.model.battlefield.Position;

import java.util.ArrayList;

/**
 * @author hwd
 * @description 前1格，左右各1格
 * @date 2020-12-26
 **/
public class LineC implements MoveStrategy {
    private static int[][] poses = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 } };

    @Override
    public ArrayList<Position> availablePos(Position position) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < poses.length; ++i) {
            Position cur = new Position(x + poses[i][0], y + poses[i][1]);
            Board board = Board.getInstance();
            if (!cur.isValid(board.getWidth(), board.getHeight()))
                continue;
            if (board.isVoid(cur) || board.isEnemy(cur, Race.Goblin))
                positions.add(cur);
        }
        return positions;
    }

}
