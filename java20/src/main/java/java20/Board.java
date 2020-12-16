package java20;

import java20.tools.Position;

public class Board {
    int[][] realBoard;
    private static Board board = new Board(10, 20);
    private Board(int w, int h){
        realBoard=new int[w][h];
    }
    public static Board getBoardInstance(){
        return board;
    }

    public void moveTo(Position p0,Position p1,int numOfCreature){
        moveTo(p0.getX(), p0.getY(), p1.getX(), p1.getY(), numOfCreature);
    }

    public void moveTo(int x0,int y0,int x1,int y1,int numOfCreature){
        setVal(x0, y0, 0);
        setVal(x1, y1, numOfCreature);
    }

    private void setVal(int x, int y, int numOfCreature){
        realBoard[x][y]=numOfCreature;
    }
}
