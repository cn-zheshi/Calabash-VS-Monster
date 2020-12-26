package java20.core;

import java20.core.model.battlefield.Board;
import java20.client.Client;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.core.model.figure.King;
import java20.core.model.figure.skill.*;
import java20.core.view.MainGUI;
import java20.core.model.figure.movestrategy.*;
import java20.core.view.MatchingGUI;
import java20.util.Race;
import java20.core.model.battlefield.Position;
import lombok.Data;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;

@Data
public class Controller {

    private MainGUI mainGUI;
    private MatchingGUI matchingGUI;

    private Race side;
    private boolean lose;
    private boolean isMyTurn;

    private static Controller controllerInstance = new Controller();

    public static Controller getInstance() {
        return controllerInstance;
    }

    private Controller() {
        this.mainGUI = MainGUI.getInstance();
        this.matchingGUI = new MatchingGUI();
        this.set();
    }

    public void set() {
        this.lose = false;

        ArrayList<King> kings = new ArrayList<>();
        ArrayList<Creature> creatures = new ArrayList<>();

        Position grandpaPosition = new Position(5, 0);
        King grandpa = new King(
                "爷爷",
                Race.Grandpa,
                grandpaPosition,
                new Periphery(),
                new Call());
        kings.add(grandpa);

        Board.getInstance().setVal(grandpaPosition, Race.Grandpa);
        Position snakePosition = new Position(5, 9);
        King snake = new King(
                "蛇精",
                Race.Snake,
                snakePosition,
                new Queen(),
                new Seduction());
        kings.add(snake);

        Board.getInstance().setVal(snakePosition, Race.Snake);
        Position scorpionPosition = new Position(4, 9);
        King scorpion = new King(
                "蝎子精",
                Race.Scorpion,
                scorpionPosition,
                new Periphery(),
                new Fading());
        kings.add(scorpion);

        Board.getInstance().setVal(scorpionPosition, Race.Scorpion);
        Position firstPosition = new Position(3, 0);
        Calabash first = new Calabash(
                "大娃",
                Race.First,
                firstPosition,
                new LineA(),
                new ShockWave());
        creatures.add(first);

        Board.getInstance().setVal(firstPosition, Race.First);
        Position secondPosition = new Position(6, 0);
        Calabash second = new Calabash(
                "二娃",
                Race.Second,
                secondPosition,
                new LineA(),
                new Insight());
        creatures.add(second);

        Board.getInstance().setVal(secondPosition, Race.Second);
        Position thirdPosition = new Position(4, 1);
        Calabash third = new Calabash(
                "三娃",
                Race.Third,
                thirdPosition,
                new SlashAndLineA(),
                new Indestructibility());
        creatures.add(third);

        Board.getInstance().setVal(thirdPosition, Race.Third);
        Position forthPosition = new Position(0, 1);
        Calabash forth = new Calabash(
                "四娃",
                Race.Forth,
                forthPosition,
                new LineB(),
                new Flame());
        creatures.add(forth);

        Board.getInstance().setVal(forthPosition, Race.Forth);
        Position fifthPosition = new Position(9, 1);
        Calabash fifth = new Calabash(
                "五娃",
                Race.Fifth,
                fifthPosition,
                new LineB(),
                new Water());
        creatures.add(fifth);

        Board.getInstance().setVal(fifthPosition, Race.Fifth);
        Position sixthPosition = new Position(3, 2);
        Calabash sixth = new Calabash(
                "六娃",
                Race.Sixth,
                sixthPosition,
                new Sun(),
                new Invisibility());
        creatures.add(sixth);

        Board.getInstance().setVal(sixthPosition, Race.Sixth);
        Position seventhPosition = new Position(6, 2);
        Calabash seventh = new Calabash(
                "七娃",
                Race.Seventh,
                seventhPosition,
                new SlashAndLineB(),
                new Cucurbit());
        creatures.add(seventh);

        Board.getInstance().setVal(seventhPosition, Race.Seventh);
        // TODO: 添加普通妖精

        Board.getInstance().set(kings, creatures, new Race[10][10]);
        this.matchingGUI.getFrame().setVisible(true);
    }

    public void go() {
        // TODO:游戏主逻辑
        Client.getInstance().go();
        MainGUI.getInstance().go();
        this.isMyTurn = (this.side == Race.Calabash);
        this.alert("匹配成功", "你的阵营:" + (this.side == Race.Calabash ? "Calabash" : "Monsters"), 500);
        while (!this.lose && !Client.getInstance().isLose()) {
            try {
                Thread.sleep(16);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainGUI.getInstance().repaint();
        }
        if (this.lose) {
            this.alert("你输了", "You Lose", 250);
        } else {
            this.alert("你赢了", "You Win", 250);
        }
    }

    private void alert(String title, String content, int width) {
        JDialog dialog = new JDialog(MainGUI.getInstance().getFrame(), title, true);
        dialog.setSize(width, 100);
        dialog.setLocationRelativeTo(null);
        JTextField text = new JTextField(content);
        text.setFont(new FontUIResource("", Font.BOLD, 50));
        text.setEditable(false);
        dialog.getContentPane().add(text);
        dialog.setVisible(true);
    }
}
