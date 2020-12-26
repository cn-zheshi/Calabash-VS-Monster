package java20;

import java20.ablility.*;
import java20.gui.MainGUI;
import java20.movestrategy.*;
import java20.tools.KindOfCreature;
import java20.tools.Position;
import java20.warrior.Calabash;
import java20.warrior.Creature;
import java20.warrior.King;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;

public class Controller {

    ArrayList<Creature> creatures;
    ArrayList<King> kings;
    KindOfCreature side;
    boolean lose;
    boolean isMyTurn;

    private static Controller controllerInstance = new Controller();

    public static Controller getInstance() {
        return controllerInstance;
    }

    public void init() {
        lose = false;
        kings = new ArrayList<>();
        creatures = new ArrayList<>();

        Position grandpaPosition = new Position(5, 0);
        King grandpa = new King(
                KindOfCreature.Grandpa,
                grandpaPosition,
                new GrandpaStrategy(),
                new GrandpaAbility());
        kings.add(grandpa);

        Board.getBoardInstance().setVal(grandpaPosition, KindOfCreature.Grandpa);
        Position snakePosition = new Position(5, 9);
        King snake = new King(
                KindOfCreature.Snake,
                snakePosition,
                new SnakeStrategy(),
                new SnakeAbility());
        kings.add(snake);

        Board.getBoardInstance().setVal(snakePosition, KindOfCreature.Snake);
        Position scorpionPosition = new Position(4, 9);
        King scorpion = new King(
                KindOfCreature.Scorpion,
                scorpionPosition,
                new ScorpionStrategy(),
                new ScorpionAbility());
        kings.add(scorpion);

        Board.getBoardInstance().setVal(scorpionPosition, KindOfCreature.Scorpion);
        Position firstPosition = new Position(3, 0);
        Calabash first = new Calabash(
                KindOfCreature.First,
                firstPosition,
                new FirstAndSecondStrategy(),
                new FirstAbility());
        creatures.add(first);

        Board.getBoardInstance().setVal(firstPosition, KindOfCreature.First);
        Position secondPosition = new Position(6, 0);
        Calabash second = new Calabash(
                KindOfCreature.Second,
                secondPosition,
                new FirstAndSecondStrategy(),
                new SecondAbility());
        creatures.add(second);

        Board.getBoardInstance().setVal(secondPosition, KindOfCreature.Second);
        Position thirdPosition = new Position(4, 1);
        Calabash third = new Calabash(
                KindOfCreature.Third,
                thirdPosition,
                new ThirdStrategy(),
                new ThirdAbility());
        creatures.add(third);

        Board.getBoardInstance().setVal(thirdPosition, KindOfCreature.Third);
        Position forthPosition = new Position(0, 1);
        Calabash forth = new Calabash(
                KindOfCreature.Forth,
                forthPosition,
                new ForthAndFifthStrategy(),
                new ForthAbility());
        creatures.add(forth);

        Board.getBoardInstance().setVal(forthPosition, KindOfCreature.Forth);
        Position fifthPosition = new Position(9, 1);
        Calabash fifth = new Calabash(
                KindOfCreature.Fifth,
                fifthPosition,
                new ForthAndFifthStrategy(),
                new FifthAbility());
        creatures.add(fifth);

        Board.getBoardInstance().setVal(fifthPosition, KindOfCreature.Fifth);
        Position sixthPosition = new Position(3, 2);
        Calabash sixth = new Calabash(
                KindOfCreature.Sixth,
                sixthPosition,
                new SixthStrategy(),
                new SixthAbility());
        creatures.add(sixth);

        Board.getBoardInstance().setVal(sixthPosition, KindOfCreature.Sixth);
        Position seventhPosition = new Position(6, 2);
        Calabash seventh = new Calabash(
                KindOfCreature.Seventh,
                seventhPosition,
                new SeventhStrategy(),
                new SeventhAbility());
        creatures.add(seventh);

        Board.getBoardInstance().setVal(seventhPosition, KindOfCreature.Seventh);
        // TODO: 添加普通妖精
    }

    private Controller() {
        this.init();
    }

    public void go() {
        // TODO:游戏主逻辑
        Client.getClientInstance().go();
        MainGUI.getMainGUIInstance().go();
        isMyTurn = (this.side == KindOfCreature.Calabash);
        tellSth("匹配成功", "你的阵营:" + (this.side == KindOfCreature.Calabash ? "Calabash" : "Monsters"), 1000);
        while (!lose && !Client.getClientInstance().isLose()) {
            try {
                Thread.sleep(16);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainGUI.getMainGUIInstance().repaint();
        }
        if (lose) {
            tellSth("你输了", "You Lose", 500);
        } else {
            tellSth("你赢了", "You Win", 500);
        }
    }

    public void setSide(KindOfCreature side) {
        this.side = side;
    }

    public KindOfCreature getSide() {
        return side;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public boolean getLose() {
        return lose;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<King> getKings() {
        return kings;
    }

    private void tellSth(String title, String content, int width) {
        JDialog dialog = new JDialog(MainGUI.getMainGUIInstance().getFrame(), title, true);
        dialog.setSize(width, 200);
        JTextField text = new JTextField(content);
        text.setFont(new FontUIResource("宋体", Font.BOLD, 100));
        text.setEditable(false);
        dialog.getContentPane().add(text);
        dialog.setVisible(true);
    }
}