package java20.movestrategy;

import java20.Board;
import java20.tools.KindOfCreature;
import java20.tools.Position;

import java.util.ArrayList;


/**
 * @author hwd
 * @date 2020-12-26
 **/
public class SnakeStrategy implements MoveStrategy {

    @Override
    public ArrayList<Position> positionsCanBeSet(int x, int y) {
        ArrayList<Position> positions = new ArrayList<>();
        Board board = Board.getBoardInstance();
        int edgeX = board.getWidth();
        int edgeY = board.getHeight();
        boolean flagA = true, flagB = true, flagC = true, flagD = true;

        for (int i = 1; i + x < edgeX || x - i >= 0; ++i) {
            Position curA = new Position(x + i, y);
            Position curB = new Position(x - i, y);

            if (flagA && curA.isValid(edgeX, edgeY)) {
                if (board.isVoid(curA)) {
                    positions.add(curA);
                } else {
                    flagA = false;
                    if (board.isEnemy(curA, KindOfCreature.Snake)) positions.add(curA);
                }
            }

            if (flagB && curB.isValid(edgeX, edgeY)) {
                if (board.isVoid(curB)) {
                    positions.add(curB);
                } else {
                    flagB = false;
                    if (board.isEnemy(curB, KindOfCreature.Snake)) positions.add(curB);
                }
            }
        }
        for (int i = 1; y + i < edgeY || y - i >= 0; ++i) {
            Position curC = new Position(x, y + i);
            Position curD = new Position(x, y - i);

            if (flagC && curC.isValid(edgeX, edgeY)) {
                if (board.isVoid(curC)) {
                    positions.add(curC);
                } else {
                    flagC = false;
                    if (board.isEnemy(curC, KindOfCreature.Snake)) positions.add(curC);
                }
            }

            if (flagD && curD.isValid(edgeX, edgeY)) {
                if (board.isVoid(curD)) {
                    positions.add(curD);
                } else {
                    flagD = false;
                    if (board.isEnemy(curD, KindOfCreature.Snake)) positions.add(curD);
                }
            }
        }
        flagA = flagB = flagC = flagD = true;
        
        for (int i = 1; x + i < edgeX || x - i >= 0 || y + i < edgeY || y - i >= 0; ++i) {
            Position curA = new Position(x + i, y + i);
            Position curB = new Position(x - i, y - i);
            Position curC = new Position(x + i, y - i);
            Position curD = new Position(x - i, y + i);

            if (flagA && curA.isValid(edgeX, edgeY)) {
                if (board.isVoid(curA)) {
                    positions.add(curA);
                } else {
                    flagA = false;
                    if (board.isEnemy(curA, KindOfCreature.Snake)) positions.add(curA);
                }
            }

            if (flagB && curB.isValid(edgeX, edgeY)) {
                if (board.isVoid(curB)) {
                    positions.add(curB);
                } else {
                    flagB = false;
                    if (board.isEnemy(curB, KindOfCreature.Snake)) positions.add(curB);
                }
            }

            if (flagC && curC.isValid(edgeX, edgeY)) {
                if (board.isVoid(curC)) {
                    positions.add(curC);
                } else {
                    flagC = false;
                    if (board.isEnemy(curC, KindOfCreature.Snake)) positions.add(curC);
                }
            }

            if (flagD && curD.isValid(edgeX, edgeY)) {
                if (board.isVoid(curD)) {
                    positions.add(curD);
                } else {
                    flagD = false;
                    if (board.isEnemy(curD, KindOfCreature.Snake)) positions.add(curD);
                }
            }
        }
        return positions;
    }

}
