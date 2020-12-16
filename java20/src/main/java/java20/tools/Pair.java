package java20.tools;

public class Pair <T,K>{
    T first;
    K second;

    public Pair(T f,K s){
        first=f;
        second=s;
    }
    
    public T getFirst(){
        return first;
    }

    public K getSecond(){
        return second;
    }

    public void setFirst(T f){
        first=f;
    }
    
    public void setSecond(K s){
        second=s;
    }
}
