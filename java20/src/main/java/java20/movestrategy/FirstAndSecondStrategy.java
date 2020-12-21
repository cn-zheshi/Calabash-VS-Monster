package java20.movestrategy;

import java.util.ArrayList;

import java20.Board;
import java20.tools.KindOfCreature;
import java20.tools.Position;

public class FirstAndSecondStrategy implements MoveStrategy {

    @Override
    public ArrayList<Position> positionsCanBeSet(int x, int y) {
        ArrayList<Position> arr=new ArrayList<Position>();
        boolean left=true;
        boolean right=true;
        boolean up=true;
        boolean down=true;
        for(int i=1;i<Board.getBoardInstance().getWidth();++i){
            if(right&&Board.getBoardInstance().isVoid(x+i, y)){
                arr.add(new Position(x+i, y));
            }
            else if(right){
                if(Board.getBoardInstance().isEnemy(x+i, y,KindOfCreature.Calabash)){
                    arr.add(new Position(x+i, y));
                }
                right=false;
            }
            if(left&&Board.getBoardInstance().isVoid(x-i, y)){
                arr.add(new Position(x-i, y));
            }
            else if(left){
                if(Board.getBoardInstance().isEnemy(x-i, y,KindOfCreature.Calabash)){
                    arr.add(new Position(x-i, y));
                }
                left=false;
            }
            if(!left&&!right){
                break;
            }
        }
        for(int i=1;i<Board.getBoardInstance().getHeight();++i){
            if(down&&Board.getBoardInstance().isVoid(x, y+i)){
                arr.add(new Position(x, y+i));
            }
            else if(down){
                if(Board.getBoardInstance().isEnemy(x, y+i,KindOfCreature.Calabash)){
                    arr.add(new Position(x, y+i));
                }
                down=false;
            }
            if(up&&Board.getBoardInstance().isVoid(x, y-i)){
                arr.add(new Position(x, y-i));
            }
            else if(up){
                if(Board.getBoardInstance().isEnemy(x, y-i,KindOfCreature.Calabash)){
                    arr.add(new Position(x, y-i));
                }
                up=false;
            }
            if(!up&&!down){
                break;
            }
        }
        return arr;
    }
    
}
