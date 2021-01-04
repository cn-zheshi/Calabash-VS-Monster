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
import java20.core.view.*;
import java20.util.GameType;
import java20.util.Race;
import lombok.Data;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

@Data
public class Controller {

    private MainGUI mainGUI;
    private LookingGUI lookingGUI;
    private MatchingGUI matchingGUI;
    private PickDialog pickGui;
    private Board board;
    private Position positionBeChosed;
    private Position unreachable;
    private GameType gameType;
    private boolean loop;
    private TurnPanel turnPanel;
    private Race side;
    private boolean lose;
    private boolean isMyTurn;
    private boolean isMoving;
    private boolean isMoved;
    private boolean isBetrayMoved;
    private int turns;

    private static Controller controllerInstance = new Controller();

    public static Controller getInstance() {
        return controllerInstance;
    }

    private Controller() {
        this.mainGUI = MainGUI.getInstance();
        this.lookingGUI = LookingGUI.getInstance();
        this.board = Board.getInstance();
        this.matchingGUI = new MatchingGUI();
        this.pickGui = null;
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
        this.loop = true;
        this.turns = 0;
        this.lose = false;
        this.isMoving = false;
        this.isMoved = false;
        isBetrayMoved = false;
        this.positionBeChosed = null;
        this.unreachable = null;

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
        King scorpion = new King("蝎子精", Race.Scorpion, scorpionPosition, new Scorpion(), new Fading(4, false, true));
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

        Position pos1 = new Position(0, 6);
        Goblin goblin1 = new Goblin("小妖精1号", Race.Goblin, pos1, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin1);
        this.board.setVal(pos1, Race.Goblin);

        Position pos2 = new Position(2, 6);
        Goblin goblin2 = new Goblin("小妖精2号", Race.Goblin, pos2, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin2);
        this.board.setVal(pos2, Race.Goblin);

        Position pos3 = new Position(3, 6);
        Goblin goblin3 = new Goblin("小妖精3号", Race.Goblin, pos3, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin3);
        this.board.setVal(pos3, Race.Goblin);

        Position pos4 = new Position(4, 6);
        Goblin goblin4 = new Goblin("小妖精4号", Race.Goblin, pos4, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin4);
        this.board.setVal(pos4, Race.Goblin);

        Position pos5 = new Position(5, 6);
        Goblin goblin5 = new Goblin("小妖精5号", Race.Goblin, pos5, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin5);
        this.board.setVal(pos5, Race.Goblin);

        Position pos6 = new Position(6, 6);
        Goblin goblin6 = new Goblin("小妖精6号", Race.Goblin, pos6, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin6);
        this.board.setVal(pos6, Race.Goblin);

        Position pos7 = new Position(7, 6);
        Goblin goblin7 = new Goblin("小妖精7号", Race.Goblin, pos7, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin7);
        this.board.setVal(pos7, Race.Goblin);

        Position pos8 = new Position(9, 6);
        Goblin goblin8 = new Goblin("小妖精8号", Race.Goblin, pos8, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin8);
        this.board.setVal(pos8, Race.Goblin);

        Position pos9 = new Position(1, 7);
        Goblin goblin9 = new Goblin("小妖精9号", Race.Goblin, pos9, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin9);
        this.board.setVal(pos9, Race.Goblin);

        Position pos10 = new Position(3, 7);
        Goblin goblin10 = new Goblin("小妖精10号", Race.Goblin, pos10, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin10);
        this.board.setVal(pos10, Race.Goblin);

        Position pos11 = new Position(6, 7);
        Goblin goblin11 = new Goblin("小妖精11号", Race.Goblin, pos11, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin11);
        this.board.setVal(pos11, Race.Goblin);

        Position pos12 = new Position(8, 7);
        Goblin goblin12 = new Goblin("小妖精12号", Race.Goblin, pos12, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin12);
        this.board.setVal(pos12, Race.Goblin);

        Position pos13 = new Position(0, 8);
        Goblin goblin13 = new Goblin("小妖精13号", Race.Goblin, pos13, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin13);
        this.board.setVal(pos13, Race.Goblin);

        Position pos14 = new Position(2, 8);
        Goblin goblin14 = new Goblin("小妖精14号", Race.Goblin, pos14, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin14);
        this.board.setVal(pos14, Race.Goblin);

        Position pos15 = new Position(4, 8);
        Goblin goblin15 = new Goblin("小妖精15号", Race.Goblin, pos15, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin15);
        this.board.setVal(pos15, Race.Goblin);

        Position pos16 = new Position(5, 8);
        Goblin goblin16 = new Goblin("小妖精16号", Race.Goblin, pos16, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin16);
        this.board.setVal(pos16, Race.Goblin);

        Position pos17 = new Position(7, 8);
        Goblin goblin17 = new Goblin("小妖精17号", Race.Goblin, pos17, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin17);
        this.board.setVal(pos17, Race.Goblin);

        Position pos18 = new Position(9, 8);
        Goblin goblin18 = new Goblin("小妖精18号", Race.Goblin, pos18, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin18);
        this.board.setVal(pos18, Race.Goblin);

        Position pos19 = new Position(3, 9);
        Goblin goblin19 = new Goblin("小妖精19号", Race.Goblin, pos19, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin19);
        this.board.setVal(pos19, Race.Goblin);

        Position pos20 = new Position(6, 9);
        Goblin goblin20 = new Goblin("小妖精20号", Race.Goblin, pos20, new LineC(), new Upgrade(-1, true, true));
        creatures.add(goblin20);
        this.board.setVal(pos20, Race.Goblin);

        this.board.set(kings, creatures);
        // this.matchingGUI.getFrame().setVisible(true);
    }

    public void go() {
        while (loop) {
            if (gameType == GameType.Playing) {
                try {
                    FileWriter fWriter = new FileWriter(new File("record.txt"));
                    fWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                    if ((getKing(0).isDead() && this.side == Race.Calabash)
                            || (getKing(1).isDead() && getKing(2).isDead() && this.side == Race.Monster)) {
                        this.lose = true;
                        Client.getInstance().sendMessage("Lose");
                    }
                }
                if (this.lose) {
                    this.alert("你输了", "You Lose", 250);
                } else {
                    this.alert("你赢了", "You Win", 250);
                }
                this.mainGUI.disable();
                SaveDialog saveDialog = new SaveDialog();
                saveDialog.go();
                GameEndDialog gameEndDialog = new GameEndDialog();
                gameEndDialog.go();
            }
            if (gameType == GameType.Looking) {
                turnPanel = new TurnPanel();
                this.lookingGUI.getFrame().getContentPane().add(turnPanel);
                this.lookingGUI.go();
                try {
                    FileReader fReader = new FileReader(new File("saved.txt"));
                    BufferedReader reader = new BufferedReader(fReader);
                    String str = reader.readLine();
                    while (str != null) {
                        if (!str.contains("UseAbility")) {
                            Thread.sleep(500);
                        }
                        processInstruction(str);
                        str = reader.readLine();
                        this.lookingGUI.repaint();
                        turnPanel.repaint();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ReplayDialog replayDialog = new ReplayDialog();
                replayDialog.go();
            }
        }
    }

    public void positionBeChosed(Position position) {
        if (isMoving) {
            Creature creature = Board.getInstance().getCreature(positionBeChosed);
            if (creature.getPosList().contains(position)) {
                creature.move(position);
                positionBeChosed = null;
                if (!creature.isTraitorous()) {
                    isMoved = true;
                } else {
                    isBetrayMoved = true;
                }
            } else {
                positionBeChosed = position;
            }
            isMoving = false;
        } else {
            positionBeChosed = position;
            return;
        }
    }

    public void move() {
        if (positionBeChosed == null) {
            return;
        }
        if (this.board.getCreature(positionBeChosed) == null) {
            return;
        }
        if (!this.board.isAlly(positionBeChosed, this.side)) {
            return;
        }
        if (this.board.getCreature(positionBeChosed).isAvailable()
                || this.board.getCreature(positionBeChosed).isALive()) {
            isMoving = true;
            return;
        }
        if (this.board.getCreature(positionBeChosed).isTraitorous() && this.side == Race.Monster && !isBetrayMoved) {
            isMoving = true;
            return;
        }
    }

    public void useAbility() {
        this.isMoving = false;
        if (positionBeChosed != null) {
            if (this.board.getCreature(positionBeChosed) == null) {
                this.alert("提示", "未选择单位", 500);
                return;
            }
            if (!this.board.isAlly(positionBeChosed, this.side)) {
                this.alert("提示", "非友方单位", 500);
                return;
            }
            if (Board.getInstance().getCreature(positionBeChosed) instanceof Calabash) {
                Calabash calabash = (Calabash) Board.getInstance().getCreature(positionBeChosed);
                if (calabash.isSkillAvailable()) {
                    calabash.employ();
                    positionBeChosed = null;
                } else {
                    if (calabash.getSkill().getLeftTime() == -1) {
                        this.alert("提示", "技能已使用", 500);
                    } else {
                        int leftTime = calabash.getSkill().getLeftTime();
                        this.alert("提示", "CD:" + leftTime + "回合", 500);
                    }
                    return;
                }
            } else if ((Board.getInstance().getCreature(positionBeChosed) instanceof King)) {
                King king = (King) Board.getInstance().getCreature(positionBeChosed);
                if (king.isSkillAvailable()) {
                    king.employ();
                    positionBeChosed = null;
                } else {
                    if (king.getSkill().getLeftTime() == -1) {
                        this.alert("提示", "技能已使用", 500);
                    } else {
                        int leftTime = king.getSkill().getLeftTime();
                        this.alert("提示", "CD:" + leftTime + "回合", 500);
                    }
                    return;
                }
            } else if ((Board.getInstance().getCreature(positionBeChosed) instanceof Goblin)) {
                Goblin goblin = (Goblin) (Board.getInstance().getCreature(positionBeChosed));
                if (goblin.getRace() == Race.Demon) {
                    this.alert("提示", "该单位无技能", 500);
                    return;
                }
                if (goblin.isSkillAvailable()) {
                    goblin.employ();
                    positionBeChosed = null;
                }
            } else {
                this.alert("提示", "该单位无技能", 500);
                return;
            }
        } else {
            this.alert("提示", "未选择单位", 500);
            return;
        }
    }

    public void processInstruction(String instruction) {
        if (instruction == null) {
            return;
        }
        if (instruction.contains("Lose")) {
            this.alert("end", "播放结束", 300);
            return;
        }
        String[] str = instruction.split("\\s+");
        if (str.length == 0) {
            return;
        }
        if (str[0].contains("Move")) {
            int x0 = Integer.parseInt(str[1].split(",")[0]);
            int y0 = Integer.parseInt(str[1].split(",")[1]);
            int x1 = Integer.parseInt(str[2].split(",")[0]);
            int y1 = Integer.parseInt(str[2].split(",")[1]);
            Creature creature = this.board.getCreature(x0, y0);
            creature.move(x1, y1);
            return;
        }
        if (str[0].contains("UseAbility")) {
            int x = Integer.parseInt(str[1].split(",")[0]);
            int y = Integer.parseInt(str[1].split(",")[1]);
            Creature creature = this.board.getCreature(x, y);
            if (creature instanceof Calabash) {
                if (!creature.getName().equals("七娃")) {
                    ((Calabash) creature).employ();
                }
                if (creature.getName().equals("六娃") && this.gameType == GameType.Playing) {
                    unreachable = creature.getPosition();
                }
            }
            if (creature instanceof Goblin) {
                ((Goblin) creature).employ();
            }
        }
        if (str[0].contains("Betray")) {
            int num = Integer.parseInt(str[1]);
            Calabash calabash = this.getCalabash(num);
            calabash.betray(1);
            this.board.setVal(calabash.getPosition(), Race.Goblin);
            this.getKing(0).intensify(1);
        }
        if (str[0].contains("Set")) {
            int num = Integer.parseInt(str[1]);
            Calabash calabash = getCalabash(num);
            if (calabash.isDead()) {
                calabash.resurge();
            }
            int x = Integer.parseInt(str[2].split(",")[0]);
            int y = Integer.parseInt(str[2].split(",")[1]);
            Position position = new Position(x, y);
            this.board.setVal(position, calabash.getRace());
            calabash.setPosition(position);
            return;
        }
        if (str[0].contains("Seal")) {
            int x = Integer.parseInt(str[1].split(",")[0]);
            int y = Integer.parseInt(str[1].split(",")[1]);
            Position position = new Position(x, y);
            Creature creature = this.board.getCreature(position);
            creature.seal(1);
        }
        if (str[0].contains("Turn-End")) {
            if (gameType == GameType.Playing) {
                isMyTurn = true;
                this.alert("Your Turn", "你的回合", 300);
            }
            if (gameType == GameType.Looking) {
                turnPanel.updateTurn();
            }
            updateTurn();
        }
    }

    public int displayPickFrame(String guiName, String[] choices, int width, int height) {
        this.pickGui = new PickDialog(guiName, choices, width, height);
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
        this.unreachable = null;
        ArrayList<Creature> creatures = this.board.getCreatures();
        int len = creatures.size();
        for (int i = 0; i < len; ++i) {
            if (i < 7) {
                ((Calabash) creatures.get(i)).getSkill().updateCooldown();
                continue;
            }
            if (((Goblin) creatures.get(i)).getSkill() != null) {
                ((Goblin) creatures.get(i)).getSkill().updateCooldown();
            }
        }
        ArrayList<King> kings = this.board.getKings();
        kings.forEach(king -> king.getSkill().updateCooldown());

        creatures.forEach(Creature::updateStatus);
        kings.forEach(Creature::updateStatus);
    }

    private void alert(String title, String content, int width) {
        JFrame frame = this.gameType == GameType.Playing ? this.mainGUI.getFrame() : this.lookingGUI.getFrame();
        JDialog dialog = new JDialog(frame, title, true);
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
