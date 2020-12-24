package java20;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import java20.ablility.FifthAbility;
import java20.ablility.FirstAbility;
import java20.ablility.ForthAbility;
import java20.ablility.GrandpaAbility;
import java20.ablility.ScorpionAbility;
import java20.ablility.SecondAbility;
import java20.ablility.SeventhAbility;
import java20.ablility.SixthAbility;
import java20.ablility.SnakeAbility;
import java20.ablility.ThirdAbility;
import java20.gui.MainGUI;
import java20.king.King;
import java20.movestrategy.FirstAndSecondStrategy;
import java20.movestrategy.ForthAndFifthStrategy;
import java20.movestrategy.GrandpaStrategy;
import java20.movestrategy.ScorpionStrategy;
import java20.movestrategy.SeventhStrategy;
import java20.movestrategy.SixthStrategy;
import java20.movestrategy.SnakeStrategy;
import java20.movestrategy.ThirdStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;
import java20.warrior.Calabash;
import java20.warrior.Creature;

import java.awt.Font;

public class Main {
    ArrayList<Creature> creatures;
    ArrayList<King> kings;
    Client client;
    KindOfCreature side;
    private static Main mainInstance = new Main();

    public static Main getMainInstance() {
        return mainInstance;
    }

    public static void main(String[] args) {
        mainInstance.go();
    }

    private Main() {
        kings = new ArrayList<King>();
        creatures = new ArrayList<Creature>();
        client = new Client();
        Position grandpaPosition = new Position(5, 0);
        King grandpa = new King(KindOfCreature.Grandpa, grandpaPosition, new GrandpaStrategy(), new GrandpaAbility());
        kings.add(grandpa);
        Position snakePosition = new Position(5, 9);
        King snake = new King(KindOfCreature.Snake, snakePosition, new SnakeStrategy(), new SnakeAbility());
        kings.add(snake);
        Position scorpionPosition = new Position(4, 9);
        King scorpion = new King(KindOfCreature.Scorpion, scorpionPosition, new ScorpionStrategy(),
                new ScorpionAbility());
        kings.add(scorpion);
        Position firstPosition = new Position(3, 0);
        Calabash first = new Calabash(KindOfCreature.First, firstPosition, new FirstAndSecondStrategy(),
                new FirstAbility());
        creatures.add(first);
        Position secondPosition = new Position(6, 0);
        Calabash second = new Calabash(KindOfCreature.Second, secondPosition, new FirstAndSecondStrategy(),
                new SecondAbility());
        creatures.add(second);
        Position thirdPosition = new Position(4, 1);
        Calabash third = new Calabash(KindOfCreature.Third, thirdPosition, new ThirdStrategy(), new ThirdAbility());
        creatures.add(third);
        Position forthPosition = new Position(0, 1);
        Calabash forth = new Calabash(KindOfCreature.Forth, forthPosition, new ForthAndFifthStrategy(),
                new ForthAbility());
        creatures.add(forth);
        Position fifthPosition = new Position(0, 9);
        Calabash fifth = new Calabash(KindOfCreature.Fifth, fifthPosition, new ForthAndFifthStrategy(),
                new FifthAbility());
        creatures.add(fifth);
        Position sixthPosition = new Position(3, 2);
        Calabash sixth = new Calabash(KindOfCreature.Sixth, sixthPosition, new SixthStrategy(), new SixthAbility());
        creatures.add(sixth);
        Position seventhPosition = new Position(6, 2);
        Calabash seventh = new Calabash(KindOfCreature.Seventh, seventhPosition, new SeventhStrategy(),
                new SeventhAbility());
        creatures.add(seventh);
        // TODO: 添加普通妖精
    }

    public void go() {
        // TODO:游戏主逻辑
        client.go();
        MainGUI.getMainGUIInstance().go();
        tellSide();
    }

    public void setSide(KindOfCreature side) {
        this.side = side;
    }

    private void tellSide() {
        JDialog dialog = new JDialog(MainGUI.getMainGUIInstance().getFrame(), "匹配成功", true);
        dialog.setSize(1000, 200);
        JTextField text = new JTextField("你的阵营: " + (this.side == KindOfCreature.Calabash ? "Calabash" : "Monsters"));
        text.setFont(new FontUIResource("宋体", Font.BOLD, 100));
        text.setEditable(false);
        dialog.getContentPane().add(text);
        dialog.setVisible(true);
    }
}
