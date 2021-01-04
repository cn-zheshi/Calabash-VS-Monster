package java20.core.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEndDialog extends JDialog {
    private JPanel panel;
    private JButton endButton;

    public GameEndDialog() {
        super(MainGUI.getInstance().getFrame(), "游戏结束", true);
        panel = new JPanel();
        endButton = new JButton("End");
    }

    public void go() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(550, 275, 300, 100);
        panel.setBackground(Color.WHITE);
        this.getContentPane().add(BorderLayout.CENTER, this.panel);
        panel.add(endButton);
        endButton.addActionListener(new EndAction());
        this.setResizable(false);
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getEndButton() {
        return endButton;
    }

    public void setEndButton(JButton endButton) {
        this.endButton = endButton;
    }

    public class EndAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameEndDialog.this.setEnabled(false);
            GameEndDialog.this.setVisible(false);
            MainGUI.getInstance().getFrame().setEnabled(false);
            MainGUI.getInstance().getFrame().setVisible(false);
            System.exit(0);
        }

    }

}
