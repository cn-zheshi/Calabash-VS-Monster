package java20.core.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author hwd
 * @date 2020-12-27
 **/
@Data
public class PickFrame extends JFrame {
    private PickPanel pickPanel;
    private boolean end;
    private int result;

    public PickFrame(String guiName, String[] choices, int width, int height) {
        super(guiName);
        this.end = false;
        this.result = -1;
        this.pickPanel = new PickPanel(choices);
        this.setGui(width, height);
    }

    public void setGui(int width, int height) {
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(550, 275, width, height);
        this.getContentPane().add(BorderLayout.CENTER, this.pickPanel);
        this.setResizable(false);
        this.setVisible(false);
    }

    public void go() {
        this.setVisible(true);
        while (!this.end) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.dispose();
    }

    public class PickPanel extends JPanel {

        public PickPanel(String[] choices) {
            this.setGui();
            int num = choices.length;
            for (int i = 0; i < num; ++i) {
                Button cur = new Button(choices[i], i);
                cur.addActionListener(e -> {
                    PickFrame.this.end = true;
                    PickFrame.this.result = cur.num;
                });
                this.add(cur);
            }
        }

        public void setGui() {
            this.setBackground(Color.WHITE);
        }
    }

    public static class Button extends JButton {
        private final int num;

        public Button(String name, int num) {
            super(name);
            this.num = num;
        }
    }

    public static void main(String[] args) {
        PickFrame test = new PickFrame("选择",
                new String[]{"大娃", "二娃", "三娃", "四娃", "五娃"}, 210, 115);
        test.go();
        System.out.println(test.getResult());
    }
}
