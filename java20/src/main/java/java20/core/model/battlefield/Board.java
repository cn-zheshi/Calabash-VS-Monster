package java20.core.model.battlefield;

import java20.core.model.figure.Creature;
import java20.core.model.figure.King;
import java20.util.Race;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {

    private ArrayList<King> kings;
    private ArrayList<Creature> creatures;

    private Race[][] grid;
    private int width, height;
    private static Board board = new Board(10, 10);

    private Board(int w, int h) {
        this.grid = new Race[w][h];
        this.width = w;
        this.height = h;
    }

    public static Board getInstance() {
        return board;
    }


    public void set(ArrayList<King> kings, ArrayList<Creature> creatures) {
        this.kings = kings;
        this.creatures = creatures;
    }

    public void clearGrid() {
        this.grid = new Race[this.width][this.height];
    }

    /**
     * @param p0:起点
     * @param p1:终点
     * @param race:生物的编号，详见Creature
     */
    public void moveTo(Position p0, Position p1, Race race) {
        moveTo(p0.getX(), p0.getY(), p1.getX(), p1.getY(), race);
    }

    /**
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param race
     */
    public void moveTo(int x0, int y0, int x1, int y1, Race race) {
        setVal(x0, y0, null);
        setVal(x1, y1, race);
    }

    public void setVal(Position p, Race race) {
        setVal(p.getX(), p.getY(), race);
    }

    public void setVal(int x, int y, Race race) {
        grid[x][y] = race;
    }

    /**
     * @param p:位置
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(Position p) {
        return isVoid(p.getX(), p.getY());
    }

    /**
     * 可以判断越界
     *
     * @param x:横坐标
     * @param y:纵坐标
     * @return 这个位置上是否已经有单位，有则返回false
     */
    public boolean isVoid(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return false;
        }
        return grid[x][y] == null;
    }

    /**
     * @param p:位置
     * @param race:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(Position p, Race race) {
        return isEnemy(p.getX(), p.getY(), race);
    }

    /**
     * @param x:横坐标
     * @param y:纵坐标
     * @param race:生物编号
     * @return 这个位置上是否有敌方单位
     */
    public boolean isEnemy(int x, int y, Race race) {
        if (x < 0 || y < 0 || x >= width || y >= height || grid[x][y] == null) {
            return false;
        }
        return race.isCalabash() || race.isGrandpa()
                ? grid[x][y].isMonster() || grid[x][y].isKingMonster()
                : grid[x][y].isCalabash() || grid[x][y].isGrandpa();
    }

    public boolean isAlly(Position p, Race race) {
        return isAlly(p.getX(), p.getY(), race);
    }

    public boolean isAlly(int x, int y, Race race) {
        if (x < 0 || y < 0 || x >= width || y >= height || grid[x][y] == null) {
            return false;
        }
        return race.isCalabash() || race.isGrandpa()
                ? grid[x][y].isCalabash() || grid[x][y].isGrandpa()
                : grid[x][y].isMonster() || grid[x][y].isKingMonster();
    }

    public Race getVal(Position p) {
        return getVal(p.getX(), p.getY());
    }

    public Race getVal(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        return grid[x][y];
    }

    public Creature getCreature(Position p) {
        int len = this.creatures.size();
        for (int i = 0; i < len; ++i) {
            Creature cur = this.creatures.get(i);
            if (cur.getPosition().equals(p)) {
                return cur;
            }
        }
        len = this.kings.size();
        for (int i = 0; i < len; ++i) {
            King cur = this.kings.get(i);
            if (cur.getPosition().equals(p)) {
                return cur;
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
