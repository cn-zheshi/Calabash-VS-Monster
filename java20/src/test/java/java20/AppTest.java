package java20;

import java20.core.model.figure.movestrategy.Queen;
import java20.core.model.battlefield.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void reachableTest() {
        ArrayList<Position> test = new Queen().availablePos(new Position(0, 0));
        int len = test.size();
        for (int i = 0; i < len; ++i)
            System.out.println(test.get(i).toString());
    }
}
