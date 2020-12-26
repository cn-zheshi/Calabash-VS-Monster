package java20.movestrategy;

import java20.Board;
import java20.tools.KindOfCreature;
import java20.tools.Position;

import java.util.ArrayList;

/**
 * @author hwd
 * @date 2020-12-26
 **/
public class ThirdStrategy implements MoveStrategy {

    private static int[][] posAround = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 1}};

    @Override
    public ArrayList<Position> positionsCanBeSet(int x, int y) {
        // TODO Auto-generated method stub
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < 8; ++i) {
            Position cur = new Position(x + posAround[i][0], y + posAround[i][1]);
            Board board = Board.getBoardInstance();
            if (!cur.isValid(board.getWidth(), board.getHeight())) continue;
            if (board.isVoid(cur) || board.isEnemy(cur, KindOfCreature.Calabash)) positions.add(cur);
        }
        return positions;
    }

}
