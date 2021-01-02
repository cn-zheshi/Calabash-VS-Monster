package java20.core.view;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.util.Race;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Data
public class LookingGUI {
    private JButton[][] buttons;
    private JFrame frame;
    private JPanel panel;
    private File f;
    private ImageIcon background;
    private ImageIcon first;
    private ImageIcon second;
    private ImageIcon third;
    private ImageIcon forth;
    private ImageIcon fifth;
    private ImageIcon sixth;
    private ImageIcon seventh;
    private ImageIcon grandpa;
    private ImageIcon monster;
    private ImageIcon strongerMonster;
    private ImageIcon snake;
    private ImageIcon scorpion;

    private static LookingGUI lookingGUI = new LookingGUI();

    private LookingGUI() {
        buttons = new JButton[10][10];
        frame = new JFrame();
        panel = new JPanel();
        f = new File(this.getClass().getResource("/").getPath());
        first = new ImageIcon(f.getPath() + "/background.png");
        setGui();
    }

    public static LookingGUI getInstance() {
        return lookingGUI;
    }

    public void setGui() {
        frame.getContentPane().add(panel);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y] = new JButton(background);
                panel.add(buttons[x][y]);
            }
        }
    }

    public void go() {
        frame.setTitle("Calabash VS Monster");
        panel.setLayout(new GridLayout(10, 10));
        panel.setSize(600, 600);
        frame.setSize(800, 635);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.repaint();
        frame.setVisible(true);
        frame.validate();
    }

    public void repaint() {
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                Race kind = Board.getInstance().getVal(x, y);
                buttons[x][y].setBackground(Color.WHITE);
                if (kind == Race.First) {
                    buttons[x][y].setIcon(first);
                    continue;
                }
                if (kind == Race.Second) {
                    buttons[x][y].setIcon(second);
                    continue;
                }
                if (kind == Race.Third) {
                    buttons[x][y].setIcon(third);
                    continue;
                }
                if (kind == Race.Forth) {
                    buttons[x][y].setIcon(forth);
                    continue;
                }
                if (kind == Race.Fifth) {
                    buttons[x][y].setIcon(fifth);
                    continue;
                }
                if (kind == Race.Sixth) {
                    buttons[x][y].setIcon(sixth);
                    continue;
                }
                if (kind == Race.Seventh) {
                    buttons[x][y].setIcon(seventh);
                    continue;
                }
                if (kind == Race.Grandpa) {
                    buttons[x][y].setIcon(grandpa);
                    continue;
                }
                if (kind == Race.Snake) {
                    buttons[x][y].setIcon(snake);
                    continue;
                }
                if (kind == Race.Scorpion) {
                    buttons[x][y].setIcon(scorpion);
                    continue;
                }
                if (kind == Race.Goblin) {
                    buttons[x][y].setIcon(monster);
                    continue;
                }
                if (kind == Race.Demon) {
                    buttons[x][y].setIcon(strongerMonster);
                    continue;
                }
                if (kind == null) {
                    buttons[x][y].setIcon(background);
                }
            }
        }
    }

    public void disable() {
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y].setEnabled(false);
            }
        }
        Controller.getInstance().setLoop(false);
    }

}
