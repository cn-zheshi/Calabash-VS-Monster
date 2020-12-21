package java20.movestrategy;

import java.util.ArrayList;

import java20.Board;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public class ForthAndFifthStrategy implements MoveStrategy {

    @Override
    public ArrayList<Position> positionsCanBeSet(int x, int y) {
        ArrayList<Position> arr=new ArrayList<Position>();
        if(Board.getBoardInstance().isVoid(x+2, y+2)||Board.getBoardInstance().isEnemy(x+2, y+2,KindOfCreature.Calabash)){
            arr.add(new Position(x+2, y+2));
        }
        if(Board.getBoardInstance().isVoid(x+2, y-2)||Board.getBoardInstance().isEnemy(x+2, y-2,KindOfCreature.Calabash)){
            arr.add(new Position(x+2, y-2));
        }
        if(Board.getBoardInstance().isVoid(x-2, y+2)||Board.getBoardInstance().isEnemy(x-2, y+2,KindOfCreature.Calabash)){
            arr.add(new Position(x-2, y+2));
        }
        if(Board.getBoardInstance().isVoid(x-2, y-2)||Board.getBoardInstance().isEnemy(x-2, y-2,KindOfCreature.Calabash)){
            arr.add(new Position(x-2, y-2));
        }
        return arr;
    }
    
}
