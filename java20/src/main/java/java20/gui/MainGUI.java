package java20.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java20.Board;
import java20.tools.KindOfCreature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GridLayout;

public class MainGUI {
    JButton[][] buttons = new JButton[10][10];
    JFrame fr = new JFrame();
    File f;
    ImageIcon background;
    ImageIcon first;
    ImageIcon second;
    ImageIcon third;
    ImageIcon forth;
    ImageIcon fifth;
    ImageIcon sixth;
    ImageIcon seventh;
    ImageIcon grandpa;
    ImageIcon monster;
    ImageIcon strongerMonster;
    ImageIcon snake;
    ImageIcon scropion;

    public static void main(String[] args) {
        new MainGUI().go();
    }

    public MainGUI() {
        f = new File(this.getClass().getResource("/").getPath());
        background = new ImageIcon(f.getPath() + "/background.png");
        // TODO: 加载图片资源
    }

    public void go() {
        fr.setTitle("Galabash VS Monster");
        fr.getContentPane().setLayout(new GridLayout(10, 10));
        fr.setSize(800, 800);
        fr.setResizable(false);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y] = new JButton(background);
                buttons[x][y].addActionListener(new ClickHandler());
                fr.getContentPane().add(buttons[x][y]);
            }
        }
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
        fr.validate();
    }

    public void repaint() {
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                KindOfCreature kind = Board.getBoardInstance().getVal(x, y);
                if (kind == KindOfCreature.First) {
                    buttons[x][y].setIcon(first);
                    continue;
                }
                if (kind == KindOfCreature.Second) {
                    buttons[x][y].setIcon(second);
                    continue;
                }
                if (kind == KindOfCreature.Third) {
                    buttons[x][y].setIcon(third);
                    continue;
                }
                if (kind == KindOfCreature.Forth) {
                    buttons[x][y].setIcon(forth);
                    continue;
                }
                if (kind == KindOfCreature.Fifth) {
                    buttons[x][y].setIcon(fifth);
                    continue;
                }
                if (kind == KindOfCreature.Sixth) {
                    buttons[x][y].setIcon(sixth);
                    continue;
                }
                if (kind == KindOfCreature.Seventh) {
                    buttons[x][y].setIcon(seventh);
                    continue;
                }
                if (kind == KindOfCreature.Grandpa) {
                    buttons[x][y].setIcon(grandpa);
                    continue;
                }
                if (kind == KindOfCreature.Snake) {
                    buttons[x][y].setIcon(snake);
                    continue;
                }
                if (kind == KindOfCreature.Scorpion) {
                    buttons[x][y].setIcon(scropion);
                    continue;
                }
                if (kind == KindOfCreature.Monster) {
                    buttons[x][y].setIcon(monster);
                    continue;
                }
                if (kind == null) {
                    buttons[x][y].setIcon(background);
                    continue;
                }
            }
        }
    }

    public class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: 处理按键
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