package java20.client;

import java20.core.Controller;
import java20.core.view.MatchingGUI;
import java20.util.Race;

import java.io.*;
import java.net.Socket;

public class Client {

    private Controller controller;

    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    boolean lose;
    FileWriter fWriter;
    private static Client client = new Client();

    private Client() {
        this.controller = Controller.getInstance();
        try {
            fWriter = new FileWriter(new File("record.txt"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lose = false;
    }

    public static Client getInstance() {
        return client;
    }

    public void go() {
        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        Thread beforePlaying = new Thread(new Before());
        beforePlaying.start();
        try {
            beforePlaying.join(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readerThread.start();
    }

    public void setUpNetworking() {
        try {
            sock = new Socket("127.0.0.1", 3000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            fWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(message);
        writer.flush();
    }

    public boolean isLose() {
        return lose;
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            System.out.println("reading");
            try {
                while (((message = reader.readLine()) != null) && !lose) {
                    if (message.equals("Lose")) {
                        lose = true;
                        break;
                    }
                    fWriter.write(message);
                    // TODO: 解析并映射至本方屏幕
                   Controller.getInstance().processInstruction(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class Before implements Runnable {

        public void run() {
            String message;
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    Controller.getInstance().getMatchingGUI().go();
                }
            });
            thread.start();
            try {
                while ((message = reader.readLine()) != null) {
                    if (message.contains("Another Player")) {
                        if (message.contains("Calabash")) {
                            Client.this.controller.setSide(Race.Calabash);
                        } else {
                            Client.this.controller.setSide(Race.Monster);
                        }
                        System.out.println(message);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            thread.stop();
            Controller.getInstance().getMatchingGUI().getFrame().setVisible(false);
        }
    }
}
