package java20.core.view;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.util.Race;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        background = new ImageIcon(f.getPath() + "/background.png");
        first = new ImageIcon(f.getPath() + "/first.png");
        second = new ImageIcon(f.getPath() + "/second.png");
        third = new ImageIcon(f.getPath() + "/third.png");
        forth = new ImageIcon(f.getPath() + "/forth.png");
        fifth = new ImageIcon(f.getPath() + "/fifth.png");
        sixth = new ImageIcon(f.getPath() + "/sixth.png");
        seventh = new ImageIcon(f.getPath() + "/seventh.png");
        grandpa = new ImageIcon(f.getPath() + "/grandpa.png");
        snake = new ImageIcon(f.getPath() + "/snake.png");
        scorpion = new ImageIcon(f.getPath() + "/scorpion.png");
        monster = new ImageIcon(f.getPath() + "/monster.png");
        strongerMonster = new ImageIcon(f.getPath() + "/strongerMonster.png");
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

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public ImageIcon getBackground() {
        return background;
    }

    public void setBackground(ImageIcon background) {
        this.background = background;
    }

    public ImageIcon getFirst() {
        return first;
    }

    public void setFirst(ImageIcon first) {
        this.first = first;
    }

    public ImageIcon getSecond() {
        return second;
    }

    public void setSecond(ImageIcon second) {
        this.second = second;
    }

    public ImageIcon getThird() {
        return third;
    }

    public void setThird(ImageIcon third) {
        this.third = third;
    }

    public ImageIcon getForth() {
        return forth;
    }

    public void setForth(ImageIcon forth) {
        this.forth = forth;
    }

    public ImageIcon getFifth() {
        return fifth;
    }

    public void setFifth(ImageIcon fifth) {
        this.fifth = fifth;
    }

    public ImageIcon getSixth() {
        return sixth;
    }

    public void setSixth(ImageIcon sixth) {
        this.sixth = sixth;
    }

    public ImageIcon getSeventh() {
        return seventh;
    }

    public void setSeventh(ImageIcon seventh) {
        this.seventh = seventh;
    }

    public ImageIcon getGrandpa() {
        return grandpa;
    }

    public void setGrandpa(ImageIcon grandpa) {
        this.grandpa = grandpa;
    }

    public ImageIcon getMonster() {
        return monster;
    }

    public void setMonster(ImageIcon monster) {
        this.monster = monster;
    }

    public ImageIcon getStrongerMonster() {
        return strongerMonster;
    }

    public void setStrongerMonster(ImageIcon strongerMonster) {
        this.strongerMonster = strongerMonster;
    }

    public ImageIcon getSnake() {
        return snake;
    }

    public void setSnake(ImageIcon snake) {
        this.snake = snake;
    }

    public ImageIcon getScorpion() {
        return scorpion;
    }

    public void setScorpion(ImageIcon scorpion) {
        this.scorpion = scorpion;
    }

    public static LookingGUI getLookingGUI() {
        return lookingGUI;
    }

    public static void setLookingGUI(LookingGUI lookingGUI) {
        LookingGUI.lookingGUI = lookingGUI;
    }
}
