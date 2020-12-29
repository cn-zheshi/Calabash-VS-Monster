package java20.core.view;

import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Creature;
import java20.client.Client;
import java20.core.Controller;
import java20.util.Race;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Data
public class MainGUI {

    private JButton[][] buttons;
    private JFrame frame;
    private JPanel panel;
    private JButton turnEndButton;
    private JButton moveButton;
    private JButton useAbilityButton;
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

    public static void main(String[] args) {
        MainGUI.getInstance().go();
    }

    private static MainGUI mainGUI = new MainGUI();

    private MainGUI() {
        buttons = new JButton[10][10];
        turnEndButton = new JButton("Turn End");
        moveButton = new JButton("Move");
        useAbilityButton = new JButton("Ability");
        frame = new JFrame();
        panel = new JPanel();
        f = new File(this.getClass().getResource("/").getPath());
        // TODO: 加载图片资源
        // background = new ImageIcon(f.getPath() + "/background.png");
        // first = new ImageIcon(f.getPath() + "/first.png");
    }

    public static MainGUI getInstance() {
        return mainGUI;
    }

    public void go() {
        frame.setTitle("Calabash VS Monster");
        frame.getContentPane().add(panel);
        frame.getContentPane().add(turnEndButton);
        frame.getContentPane().add(moveButton);
        frame.getContentPane().add(useAbilityButton);
        panel.setLayout(new GridLayout(10, 10));
        panel.setSize(600, 600);
        useAbilityButton.addActionListener(new UseAbilityButton());
        useAbilityButton.setSize(120, 40);
        useAbilityButton.setLocation(640, 300);
        moveButton.addActionListener(new MoveButton());
        moveButton.setSize(120, 40);
        moveButton.setLocation(640, 100);
        turnEndButton.addActionListener(new TurnEndButton());
        turnEndButton.setSize(120, 40);
        turnEndButton.setLocation(640, 500);
        frame.setSize(800, 635);
        frame.setLayout(null);
        frame.setResizable(false);
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 10; ++x) {
                buttons[x][y] = new JButton(background);
                buttons[x][y].addActionListener(new ClickHandler());
                panel.add(buttons[x][y]);
            }
        }
        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                if (kind == null) {
                    buttons[x][y].setIcon(background);
                }
            }
        }
        if (Controller.getInstance().isMoving()) {
            Position p = Controller.getInstance().getPositionBeChosed();
            Creature creature = Board.getInstance().getCreature(p);
            for (int i = 0; i < creature.getPosList().size(); ++i) {
                Position position = creature.getPosList().get(i);
                buttons[position.getX()][position.getY()].setBackground(Color.GREEN);
            }
        }
    }

    public class ClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Controller.getInstance().isMyTurn()) {
                for (int y = 0; y < 10; ++y) {
                    for (int x = 0; x < 10; ++x) {
                        if (e.getSource().equals(buttons[x][y])) {
                            Controller.getInstance().positionBeChosed(new Position(x, y));
                        }
                    }
                }
            }
        }
    }

    public class MoveButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Controller.getInstance().isMyTurn() && !Controller.getInstance().isMoving()
                    && !Controller.getInstance().isMoved()) {
                Controller.getInstance().setIsMoving(true);
            }
        }

    }

    public class UseAbilityButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Controller.getInstance().isMyTurn()) {
                Controller.getInstance().useAbility();
            }
        }

    }

    public class TurnEndButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Controller.getInstance().isMyTurn()) {
                Controller controller = Controller.getInstance();
                controller.setMyTurn(false);
                controller.updateTurn();
            }
        }

    }
}