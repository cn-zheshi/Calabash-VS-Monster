package java20.tools;

public class Position {
    int x,y;
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Position(){
        this(0,0);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
}
