package java20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    boolean lose=false;
    public void setUpNetworking(){
        try{
            sock=new Socket("127.0.0.1", 3000);
            InputStreamReader streamReader=new InputStreamReader(sock.getInputStream());
            reader=new BufferedReader(streamReader);
            writer=new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public class IncomingReader implements Runnable{
        public void run(){
            String message;
            try {
                while(((message=reader.readLine())!=null)&&!lose){
                    if(message.equals("Lose")){
                        lose=true;
                        break;
                    }
                    String[] arr=message.split(",");
                    //写文件
                    //
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
