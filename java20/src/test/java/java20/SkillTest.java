package java20;

import java20.core.Controller;
import java20.core.model.battlefield.Board;
import java20.core.model.battlefield.Position;
import java20.core.model.figure.Calabash;
import java20.core.model.figure.King;
import java20.util.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author hwd
 * @date 2020-12-27
 **/
public class SkillTest {

    Controller controller;
    Board board;

    @Before
    public void setGui() {
        this.controller = Controller.getInstance();
        this.board = this.controller.getBoard();
    }

    @After
    public void reset() {
        this.controller.reset();
    }

    @Test
    public void CallTest() {
        King grandpa = this.controller.getKing(0);
        System.out.println(this.controller.getKing(0).getName());
        System.out.println(this.controller.getKing(0).getPosition());
        this.controller.getCalabash(1).setStatus(Status.DEAD);
        this.controller.getCalabash(6).setPosition(new Position(9, 9));
        for (int i = 0; i < 7; ++i) {
            System.out.println(this.controller.getCalabash(i).getName());
            System.out.println(this.controller.getCalabash(i).getPosition());
            System.out.println(this.controller.getCalabash(i).getStatus());
        }
        grandpa.getSkill().employ(grandpa);
        for (int i = 0; i < 7; ++i) {
            System.out.println(this.controller.getCalabash(i).getName());
            System.out.println(this.controller.getCalabash(i).getPosition());
            System.out.println(this.controller.getCalabash(i).getStatus());
        }
        assertFalse(grandpa.getSkill().isAvailable());
        assertEquals(-1, grandpa.getSkill().getLeftTime());
    }

    @Test
    public void CucurbitTest() {
        Calabash seven = this.controller.getCalabash(6);
        King snake = this.controller.getKing(1);
        seven.getSkill().employ(seven);
        System.out.println(this.controller.getKing(1).getStatus());
        System.out.println(this.controller.getKing(2).getStatus());
        System.out.println(this.controller.getKing(0).getStatus());
        assertFalse(seven.getSkill().isAvailable());
        assertEquals(5, seven.getSkill().getLeftTime());
        seven.getSkill().modifyCooldown(-5);
        assertTrue(seven.getSkill().isAvailable());
        seven.getSkill().employ(snake);
        System.out.println(this.controller.getKing(1).getStatus());
        System.out.println(this.controller.getKing(2).getStatus());
        System.out.println(this.controller.getKing(0).getStatus());
        assertFalse(seven.getSkill().isAvailable());
        assertEquals(5, seven.getSkill().getLeftTime());
    }

    @Test
    public void FadingTest() {
        King scorpion = this.controller.getKing(2);
        for (int i = 0; i < 7; ++i) {
            System.out.println(this.controller.getCalabash(i).getName());
            System.out.println(this.controller.getCalabash(i).getPosition());
            System.out.println(this.controller.getCalabash(i).getStatus());
        }
        scorpion.getSkill().employ(scorpion);
        for (int i = 0; i < 7; ++i) {
            System.out.println(this.controller.getCalabash(i).getName());
            System.out.println(this.controller.getCalabash(i).getPosition());
            System.out.println(this.controller.getCalabash(i).getStatus());
        }
        assertFalse(scorpion.getSkill().isAvailable());
        assertEquals(4, scorpion.getSkill().getLeftTime());
    }
}
