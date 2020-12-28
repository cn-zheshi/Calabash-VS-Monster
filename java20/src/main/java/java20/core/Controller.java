package java20.core;

import java20.client.Client;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.Creature;
import java20.core.model.figure.Goblin;
import java20.core.model.figure.King;
import java20.core.model.figure.movestrategy.*;
import java20.core.model.figure.skill.*;
import java20.core.view.MainGUI;
import java20.core.view.MatchingGUI;
import java20.core.view.PickFrame;
import java20.util.Race;
import lombok.Data;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;

@Data
public class Controller {

    private MainGUI mainGUI;
    private MatchingGUI matchingGUI;
    private PickFrame pickGui;
    private Board board;

    private Race side;
    private boolean lose;
    private boolean isMyTurn;
    private boolean isMoving;
    private boolean isMoved;
    private int turns;

    private static Controller controllerInstance = new Controller();

    public static Controller getInstance() {
        return controllerInstance;
    }

    private Controller() {
        this.mainGUI = MainGUI.getInstance();
        this.board = Board.getInstance();
        this.matchingGUI = new MatchingGUI();
        this.pickGui = null;
        this.turns = 0;
        this.set();
    }

    public void reset() {
        this.set();
    }

    /**
     * 初始化位置固定 kings中 第一个为爷爷 第二个为蛇精 第三个为蝎子精 creatures中 1-7按顺序为大娃...七娃 后边都是妖精
     * 妖精1号...妖精20号
     */
    public void set() {
        this.lose = false;
        this.isMoving = false;
        this.isMoved = false;

        ArrayList<King> kings = new ArrayList<>();
        ArrayList<Creature> creatures = new ArrayList<>();
        this.board.clearGrid();

        Position grandpaPosition = new Position(5, 0);
        King grandpa = new King("爷爷", Race.Grandpa, grandpaPosition, new Periphery(), new Call(-1, true, true));
        kings.add(grandpa);

        this.board.setVal(grandpaPosition, Race.Grandpa);
        Position snakePosition = new Position(5, 9);
        King snake = new King("蛇精", Race.Snake, snakePosition, new Queen(), new Seduction(10, false, true));
        kings.add(snake);

        this.board.setVal(snakePosition, Race.Snake);
        Position scorpionPosition = new Position(4, 9);
        King scorpion = new King("蝎子精", Race.Scorpion, scorpionPosition, new Periphery(), new Fading(4, false, true));
        kings.add(scorpion);

        this.board.setVal(scorpionPosition, Race.Scorpion);
        Position firstPosition = new Position(3, 0);
        Calabash first = new Calabash("大娃", Race.First, firstPosition, new LineA(), new ShockWave(5, false, true));
        creatures.add(first);

        this.board.setVal(firstPosition, Race.First);
        Position secondPosition = new Position(6, 0);
        Calabash second = new Calabash("二娃", Race.Second, secondPosition, new LineA(), new Insight(-1, true, true));
        creatures.add(second);

        this.board.setVal(secondPosition, Race.Second);
        Position thirdPosition = new Position(4, 1);
        Calabash third = new Calabash("三娃", Race.Third, thirdPosition, new SlashAndLineA(),
                new Indestructibility(10, false, true));
        creatures.add(third);

        this.board.setVal(thirdPosition, Race.Third);
        Position forthPosition = new Position(0, 1);
        Calabash forth = new Calabash("四娃", Race.Forth, forthPosition, new LineB(), new Flame(5, false, true));
        creatures.add(forth);

        this.board.setVal(forthPosition, Race.Forth);
        Position fifthPosition = new Position(9, 1);
        Calabash fifth = new Calabash("五娃", Race.Fifth, fifthPosition, new LineB(), new Water(5, false, true));
        creatures.add(fifth);

        this.board.setVal(fifthPosition, Race.Fifth);
        Position sixthPosition = new Position(3, 2);
        Calabash sixth = new Calabash("六娃", Race.Sixth, sixthPosition, new Sun(), new Invisibility(5, false, true));
        creatures.add(sixth);

        this.board.setVal(sixthPosition, Race.Sixth);
        Position seventhPosition = new Position(6, 2);
        Calabash seventh = new Calabash("七娃", Race.Seventh, seventhPosition, new SlashAndLineB(),
                new Cucurbit(5, false, true));
        creatures.add(seventh);

        this.board.setVal(seventhPosition, Race.Seventh);
        // TODO: 添加普通妖精

        this.board.set(kings, creatures);
        this.matchingGUI.getFrame().setVisible(true);
    }

    public void go() {
        // TODO:游戏主逻辑
        Client.getInstance().go();
        this.mainGUI.go();
        this.isMyTurn = (this.side == Race.Calabash);
        this.alert("匹配成功", "你的阵营:" + (this.side == Race.Calabash ? "Calabash" : "Monsters"), 500);
        while (!this.lose && !Client.getInstance().isLose()) {
            try {
                Thread.sleep(16);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mainGUI.repaint();
        }
        if (this.lose) {
            this.alert("你输了", "You Lose", 250);
        } else {
            this.alert("你赢了", "You Win", 250);
        }
    }

    public void positionBeChosed(Position position) {
        // TODO: 处理按键
    }

    public int displayPickFrame(String guiName, String[] choices, int width, int height) {
        this.pickGui = new PickFrame(guiName, choices, width, height);
        this.pickGui.go();
        return this.pickGui.getResult();
    }

    public Calabash getCalabash(int num) {
        return (Calabash) this.board.getCreatures().get(num);
    }

    public King getKing(int num) {
        return this.board.getKings().get(num);
    }

    /**
     * @param num 表示第几个妖精 因为在creature数组中goblin存在calabash后边 所以+7
     * @return Goblin
     */
    public Goblin getGoblin(int num) {
        return (Goblin) this.board.getCreatures().get(num + 7);
    }

    public void updateTurn() {
        ++this.turns;
        ArrayList<Creature> creatures = this.board.getCreatures();
        int len = creatures.size();
        for (int i = 0; i < len; ++i) {
            if (i < 7) {
                ((Calabash) creatures.get(i)).getSkill().updateCooldown();
                continue;
            }
            ((Goblin) creatures.get(i)).getSkill().updateCooldown();
        }
        ArrayList<King> kings = this.board.getKings();
        kings.forEach(king -> king.getSkill().updateCooldown());

        creatures.forEach(Creature::updateStatus);
        kings.forEach(Creature::updateStatus);
    }

    private void alert(String title, String content, int width) {
        JDialog dialog = new JDialog(this.mainGUI.getFrame(), title, true);
        dialog.setSize(width, 100);
        dialog.setLocationRelativeTo(null);
        JTextField text = new JTextField(content);
        text.setFont(new FontUIResource("", Font.BOLD, 50));
        text.setEditable(false);
        dialog.getContentPane().add(text);
        dialog.setVisible(true);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setIsMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

}
