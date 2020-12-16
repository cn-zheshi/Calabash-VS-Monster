package java20.movestrategy;

import java.util.ArrayList;

import java20.Board;
import java20.tools.Pair;

public interface MoveStrategy {
    Board board=Board.getBoardInstance();
    ArrayList<Pair<Integer,Integer>> move(int x,int y);
}
