package java20.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java20.Board;
import java20.Main;
import java20.tools.KindOfCreature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GridLayout;

public class MainGUI {
    JButton[][] buttons;
    JFrame fr;
    JPanel panel;
    JButton turnEndButton;
    JButton moveButton;
    JButton useAbilityButton;
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
        MainGUI.getMainGUIInstance().go();
    }

    private static MainGUI mainGUI = new MainGUI();

    private MainGUI() {
        buttons = new JButton[10][10];
        turnEndButton = new JButton("Turn End");
        fr = new JFrame();
        panel = new JPanel();
        f = new File(this.getClass().getResource("/").getPath());
        background = new ImageIcon(f.getPath() + "/background.png");
        // TODO: 加载图片资源
        first = new ImageIcon(f.getPath() + "/first.png");
    }

    public static MainGUI getMainGUIInstance() {
        return mainGUI;
    }

    public void go() {
        fr.setTitle("Galabash VS Monster");
        fr.getContentPane().add(panel);
        fr.getContentPane().add(turnEndButton);
        panel.setLayout(new GridLayout(10, 10));
        panel.setSize(800, 800);
        turnEndButton.addActionListener(new TurnEndButton());
        turnEndButton.setSize(100, 50);
        turnEndButton.setLocation(850, 700);
        fr.setSize(1000, 835);
        fr.setLayout(null);
        fr.setResizable(false);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y] = new JButton(background);
                buttons[x][y].addActionListener(new ClickHandler());
                panel.add(buttons[x][y]);
            }
        }
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        repaint();
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

    public JFrame getFrame() {
        return fr;
    }

    public class ClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: 处理按键
            if (Main.getMainInstance().isMyTurn()) {
                for (int y = 0; y < 10; ++y) {
                    for (int x = 0; x < 10; ++x) {
                        if (e.getSource().equals(buttons[x][y])) {
                            if (Board.getBoardInstance().isAlly(x, y, Main.getMainInstance().getSide())) {
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

    }

    public class TurnEndButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.getMainInstance().setIsMyTurn(false);
        }

    }
}