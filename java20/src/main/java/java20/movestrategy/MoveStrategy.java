package java20.movestrategy;

import java.util.ArrayList;

import java20.Board;
import java20.tools.Position;

public interface MoveStrategy {
    ArrayList<Position> positionsCanBeSet(int x,int y);
}
