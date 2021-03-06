package java20.core.view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class MatchingGUI {

    private JFrame frame = new JFrame();
    private JTextArea jtextArea = new JTextArea("正在匹配对手");

    public static void main(String[] args) {
        new MatchingGUI().go();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextArea getJtextArea() {
        return jtextArea;
    }

    public void setJtextArea(JTextArea jtextArea) {
        this.jtextArea = jtextArea;
    }

    public void go() {
        frame.setTitle("Calabash VS Monster");
        frame.setSize(400, 94);
        frame.setLocationRelativeTo(null);
        jtextArea.setFont(new FontUIResource("", Font.BOLD, 40));
        jtextArea.setEditable(false);
        frame.getContentPane().add(jtextArea);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.validate();
        try {
            int count = 0;
            while (true) {
                Thread.sleep(300);
                if (count != 3) {
                    jtextArea.setText(jtextArea.getText() + ".");
                } else {
                    jtextArea.setText("正在匹配对手");
                    count = -1;
                }
                ++count;
            }
        } catch (Exception e) {
            this.frame.setEnabled(false);
            this.frame.setVisible(false);
            this.frame.dispose();
        }
    }
}
