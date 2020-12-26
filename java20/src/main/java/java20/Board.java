package java20;

import java20.tools.KindOfCreature;
import java20.tools.Position;
import java20.warrior.Creature;

public class Board {

    KindOfCreature[][] realBoard;
    int width, height;
    private static Board board = new Board(10, 10);

    private Board(int w, int h) {
        realBoard = new KindOfCreature[w][h];
        width = w;
        height = h;
    }

    public static Board getBoardInstance() {
        return board;
    }

    /**
     * @param p0:起点
     * @param p1:终点
     * @param kindOfCreature:生物的编号，详见Creature
     */
    public void moveTo(Position p0, Position p1, KindOfCreature kindOfCreature) {
        moveTo(p0.getX(), p0.getY(), p1.getX(), p1.getY(), kindOfCreature);
    }

    /**
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param kindOfCreature
     */
    public void moveTo(int x0, int y0, int x1, int y1, KindOfCreature kindOfCreature) {
        setVal(x0, y0, null);
        setVal(x1, y1, kindOfCreature);
    }

    public void setVal(Position p, KindOfCreature kindOfCreature) {
        setVal(p.getX(), p.getY(), kindOfCreature);
    }

    public void setVal(int x, int y, KindOfCreature kindOfCreature) {
        realBoard[x][y] = kindOfCreature;
    }

    /**
     * @param p:位置
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(Position p) {
        return isVoid(p.getX(), p.getY());
    }

    /**
     * @param x:横坐标
     * @param y:纵坐标
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        return realBoard[x][y] == null;
    }

    /**
     * @param p:位置
     * @param kindOfCreature:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(Position p, KindOfCreature kindOfCreature) {
        return isEnemy(p.getX(), p.getY(), kindOfCreature);
    }

    /**
     * @param x:横坐标
     * @param y:纵坐标
     * @param kindOfCreature:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(int x, int y, KindOfCreature kindOfCreature) {
        if (x < 0 || y < 0 || x >= width || y >= height || realBoard[x][y] == null) {
            return false;
        }
        return kindOfCreature.isCalabash() || kindOfCreature.isGrandpa()
                ? realBoard[x][y].isMonster() || realBoard[x][y].isKingMonster()
                : realBoard[x][y].isCalabash() || realBoard[x][y].isGrandpa();
    }

    public boolean isAlly(Position p, KindOfCreature kindOfCreature) {
        return isAlly(p.getX(), p.getY(), kindOfCreature);
    }

    public boolean isAlly(int x, int y, KindOfCreature kindOfCreature) {
        if (x < 0 || y < 0 || x >= width || y >= height || realBoard[x][y] == null) {
            return false;
        }
        return kindOfCreature.isCalabash() || kindOfCreature.isGrandpa()
                ? realBoard[x][y].isCalabash() || realBoard[x][y].isGrandpa()
                : realBoard[x][y].isMonster() || realBoard[x][y].isKingMonster();
    }

    public KindOfCreature getVal(Position p) {
        return getVal(p.getX(), p.getY());
    }

    public KindOfCreature getVal(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        return realBoard[x][y];
    }

    public Creature getCreature(Position p) {
        for (int i = 0; i < Controller.getInstance().getCreatures().size(); ++i) {
            if (Controller.getInstance().getCreatures().get(i).getPosition().equals(p)) {
                return Controller.getInstance().getCreatures().get(i);
            }
        }
        return null;
    }

    public Creature getCreature(int x, int y) {
        return getCreature(new Position(x, y));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
