package java20.core.view;

import java20.core.Controller;

import javax.swing.*;
import java.awt.*;

public class TurnPanel extends JPanel {
    private int turn;

    public TurnPanel() {
        super();
        turn = Controller.getInstance().getTurns();
        this.setBackground(Color.WHITE);
        this.setLocation(600, 35);
        this.setSize(200, 600);
    }

    public void updateTurn() {
        ++this.turn;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 200, 600);
        g.setColor(Color.RED);
        g.fillOval(50, turn % 2 == 0 ? 50 : 450, 100, 100);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
