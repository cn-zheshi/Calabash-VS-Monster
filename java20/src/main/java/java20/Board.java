package java20;

public class Board {
    int[][] realBoard;
    private static Board board = new Board(10, 20);
    private Board(int w, int h){
        realBoard=new int[w][h];
    }
    public static Board getBoardInstance(){
        return board;
    }
}
