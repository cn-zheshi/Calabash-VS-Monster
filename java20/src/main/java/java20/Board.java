package java20;

import java20.tools.KindOfCreature;
import java20.tools.Position;

public class Board {
    KindOfCreature[][] realBoard;
    int width,height;
    private static Board board = new Board(10, 20);
    private Board(int w, int h){
        realBoard=new KindOfCreature[w][h];
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
    public void moveTo(Position p0,Position p1,KindOfCreature kindOfCreature){
        moveTo(p0.getX(), p0.getY(), p1.getX(), p1.getY(), kindOfCreature);
    }
    /**
     * 
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param numOfCreature
     */
    public void moveTo(int x0,int y0,int x1,int y1,KindOfCreature kindOfCreature){
        setVal(x0, y0, null);
        setVal(x1, y1, kindOfCreature);
    }

    private void setVal(int x, int y, KindOfCreature kindOfCreature){
        realBoard[x][y]=kindOfCreature;
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
        return realBoard[x][y]==null;
    }
    /**
     * 
     * @param p:位置
     * @param numOfCreature:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(Position p,KindOfCreature kindOfCreature){
        return isEnemy(p.getX(), p.getY(), kindOfCreature);
    }
    /**
     * 
     * @param x:横坐标
     * @param y:纵坐标
     * @param kindOfCreature:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(int x,int y,KindOfCreature kindOfCreature){
        return kindOfCreature.isCalabash()?kindOfCreature.isMonster():realBoard[x][y].isCalabash();
    }
}
