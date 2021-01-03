package java20.core.model.figure.movestrategy;

import java20.core.model.battlefield.Position;
import java20.util.Race;

import java.util.ArrayList;

public interface MoveStrategy {
  
    public ArrayList<Position> availablePos(Position position, Race race);
}
