package java20;

import java20.tools.Position;

public class Board {
    int[][] realBoard;
    int width,height;
    private static Board board = new Board(10, 20);
    private Board(int w, int h){
        realBoard=new int[w][h];
        width=w;
        height=h;
    }
    public static Board getBoardInstance(){
        return board;
    }
    /**
     * 
     * @param p0:起点
     * @param p1:终点
     * @param numOfCreature:生物的编号，详见Creature
     */
    public void moveTo(Position p0,Position p1,int numOfCreature){
        moveTo(p0.getX(), p0.getY(), p1.getX(), p1.getY(), numOfCreature);
    }
    /**
     * 
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param numOfCreature
     */
    public void moveTo(int x0,int y0,int x1,int y1,int numOfCreature){
        setVal(x0, y0, 0);
        setVal(x1, y1, numOfCreature);
    }

    private void setVal(int x, int y, int numOfCreature){
        realBoard[x][y]=numOfCreature;
    }
    /**
     * 
     * @param p:位置
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(Position p){
        return isVoid(p.getX(), p.getY());
    }
    /**
     * 
     * @param x:横坐标
     * @param y:纵坐标
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(int x,int y){
        if(x<0||y<0||x>=width||y>=height){
            return false;
        }
        return realBoard[x][y]==0;
    }
}
