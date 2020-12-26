package java20.movestrategy;

import java20.Board;
import java20.tools.KindOfCreature;
import java20.tools.Position;

import java.util.ArrayList;

public class ForthAndFifthStrategy implements MoveStrategy {

    @Override
    public ArrayList<Position> positionsCanBeSet(int x, int y) {
        ArrayList<Position> arr = new ArrayList<Position>();
        if (Board.getBoardInstance().isVoid(x + 2, y + 2)
                || Board.getBoardInstance().isEnemy(x + 2, y + 2, KindOfCreature.Calabash)) {
            Position cur = new Position(x + 2, y + 2);
            if (cur.isValid(Board.getBoardInstance().getWidth(), Board.getBoardInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getBoardInstance().isVoid(x + 2, y - 2)
                || Board.getBoardInstance().isEnemy(x + 2, y - 2, KindOfCreature.Calabash)) {
            Position cur = new Position(x + 2, y - 2);
            if (cur.isValid(Board.getBoardInstance().getWidth(), Board.getBoardInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getBoardInstance().isVoid(x - 2, y + 2)
                || Board.getBoardInstance().isEnemy(x - 2, y + 2, KindOfCreature.Calabash)) {
            Position cur = new Position(x - 2, y + 2);
            if (cur.isValid(Board.getBoardInstance().getWidth(), Board.getBoardInstance().getHeight()))
                arr.add(cur);
        }
        if (Board.getBoardInstance().isVoid(x - 2, y - 2)
                || Board.getBoardInstance().isEnemy(x - 2, y - 2, KindOfCreature.Calabash)) {
            Position cur = new Position(x - 2, y - 2);
            if (cur.isValid(Board.getBoardInstance().getWidth(), Board.getBoardInstance().getHeight()))
                arr.add(cur);
        }
        return arr;
    }

}
