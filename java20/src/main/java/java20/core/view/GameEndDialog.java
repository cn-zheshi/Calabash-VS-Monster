package java20.core.view;

import lombok.Data;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

@Data
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
