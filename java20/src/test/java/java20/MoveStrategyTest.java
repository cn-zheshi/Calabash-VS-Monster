package java20;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.movestrategy.*;
import java20.util.Race;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class MoveStrategyTest {

    private Controller controller;
    private Board board;

    @Before
    public void init() {
        this.controller = Controller.getInstance();
        this.board = this.controller.getBoard();
    }

    @Test
    public void queueTest() {
        ArrayList<Position> test = new Queen().availablePos(new Position(0, 0), Race.Snake);
        for (Position position : test) System.out.println(position.toString());
    }

    @Test
    public void lineTest() {
        ArrayList<Position> testA = new LineA().availablePos(new Position(3, 8), Race.Calabash);
        for (Position position : testA) System.out.println(position.toString());
        System.out.println("===");
        ArrayList<Position> testB = new LineB().availablePos(new Position(3, 8), Race.Calabash);
        for (Position position : testB) System.out.println(position.toString());
        System.out.println("===");
        ArrayList<Position> testC = new LineC().availablePos(new Position(3, 8), Race.Calabash);
        for (Position position : testC) System.out.println(position.toString());
    }

    @Test
    public void peripheryTest() {
        ArrayList<Position> testA = new Periphery().availablePos(new Position(0, 0), Race.Scorpion);
        for (Position position : testA) System.out.println(position.toString());
        System.out.println("===");
        ArrayList<Position> testB = new Periphery().availablePos(new Position(2, 4), Race.Grandpa);
        for (Position position : testB) System.out.println(position.toString());
    }

    @Test
    public void slashTest() {
        ArrayList<Position> testA = new SlashA().availablePos(new Position(0, 0), Race.Demon);
        for (Position position : testA) System.out.println(position.toString());
    }

    @Test
    public void slashAndLineTest() {
        ArrayList<Position> testA = new SlashAndLineA().availablePos(new Position(0, 0), Race.Third);
        for (Position position : testA) System.out.println(position.toString());
        System.out.println("===");
        ArrayList<Position> testB = new SlashAndLineB().availablePos(new Position(0, 0), Race.Seventh);
        for (Position position : testB) System.out.println(position.toString());

    }

    @Test
    public void sunTest() {
        ArrayList<Position> testA = new Sun().availablePos(new Position(2, 5), Race.Sixth);
        for (Position position : testA) System.out.println(position.toString());
    }

}
