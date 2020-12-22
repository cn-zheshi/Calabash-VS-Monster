package java20.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.FontUIResource;

import java.awt.Font;

public class MatchingGUI {
    JFrame fr = new JFrame();
    JTextArea textArea = new JTextArea("正在匹配对手");

    public static void main(String[] args) {
        new MatchingGUI().go();
    }

    public void go() {
        fr.setTitle("Galabash VS Monster");
        fr.setSize(1000, 150);
        textArea.setFont(new FontUIResource("宋体", Font.BOLD, 100));
        textArea.setEditable(false);
        fr.getContentPane().add(textArea);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.validate();
        try {
            int count = 0;
            while (true) {
                Thread.sleep(300);
                if (count != 3) {
                    textArea.setText(textArea.getText() + ".");
                } else {
                    textArea.setText("正在匹配对手");
                    count = -1;
                }
                ++count;
            }
        } catch (Exception e) {

        }
    }
}
