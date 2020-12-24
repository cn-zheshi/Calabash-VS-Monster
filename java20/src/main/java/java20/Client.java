package java20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java20.gui.MatchingGUI;
import java20.tools.KindOfCreature;

public class Client {
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    boolean lose;
    private static Client client = new Client();

    private Client() {
        lose = false;
    }

    public static Client getClientInstance(){
        return client;
    }
    public void go() {
        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        Thread beforePlaying = new Thread(new BeforePlaying());
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
        writer.println(message);
        writer.flush();
    }

    public boolean isLose() {
        return lose;
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while (((message = reader.readLine()) != null) && !lose) {
                    if (message.equals("Lose")) {
                        lose = true;
                        break;
                    }
                    String[] arr = message.split(",");
                    FileWriter fWriter = new FileWriter(new File("record.txt"));
                    for (int i = 0; i < arr.length - 1; ++i) {
                        fWriter.write(arr[i] + "\n");
                    }
                    // TODO: 解析并映射至本方屏幕
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class BeforePlaying implements Runnable {
        public void run() {
            String message;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    MatchingGUI matching = new MatchingGUI();
                    matching.go();
                }
            });
            thread.start();
            try {
                while ((message = reader.readLine()) != null) {
                    if (message.contains("Another Player")) {
                        if (message.contains("Calabash")) {
                            Main.getMainInstance().setSide(KindOfCreature.Calabash);
                        } else {
                            Main.getMainInstance().setSide(KindOfCreature.Monsters);
                        }
                        System.out.println(message);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
