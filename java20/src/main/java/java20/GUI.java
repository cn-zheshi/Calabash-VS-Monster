package java20;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GridLayout;

public class GUI {
    JButton[][] buttons = new JButton[10][10];
    JFrame fr = new JFrame();

    public static void main(String[] args) {
        new GUI().go();
    }

    public void go() {
        fr.setTitle("载入图片");
        File f = new File(this.getClass().getResource("/").getPath());
        ImageIcon icon = new ImageIcon(f.getPath() + "/background.png");
        fr.getContentPane().setLayout(new GridLayout(10, 10));
        fr.setSize(800, 800);
        fr.setResizable(false);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y] = new JButton(icon);
                buttons[x][y].addActionListener(new ClickHandler());
                fr.getContentPane().add(buttons[x][y]);
            }
        }
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
        fr.validate();
    }

    public class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int y = 0; y < 10; ++y) {
                for (int x = 0; x < 10; ++x) {
                    if (e.getSource().equals(buttons[x][y])) {
                        JDialog dialog = new JDialog(fr, "问题" + (y * 10 + x + 1), true);
                        dialog.setSize(200, 100);
                        JTextField text = new JTextField("some questions");
                        text.setEditable(false);
                        dialog.getContentPane().add(text);
                        dialog.setVisible(true);
                    }
                }
            }
        }
    }
}