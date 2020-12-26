package java20.core.model.battlefield;

import lombok.Data;

@Data
public class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this(0, 0);
    }

    /**
     * 默认边界为 0 - edge 不包括 edge
     *
     * @param edgeX x的上界
     * @param edgeY y的上界
     * @return 是否越界
     */
    public boolean isValid(int edgeX, int edgeY) {
        if (this.x < 0 || this.x >= edgeX) return false;
        if (this.y < 0 || this.y >= edgeY) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && o instanceof Position) {
            Position position = (Position) o;
            return position.getX() == x && position.getY() == y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
