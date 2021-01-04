package java20.core.view;

import java20.core.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayDialog extends JDialog {
    private JPanel panel;
    private JButton cancelButton;
    private JButton replayButton;

    public ReplayDialog() {
        super(LookingGUI.getInstance().getFrame(), "replay", true);
        panel = new JPanel();
        cancelButton = new JButton("Cancel");
        replayButton = new JButton("Replay");
    }

    public void go() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(550, 275, 300, 150);
        panel.setBackground(Color.WHITE);
        this.getContentPane().add(BorderLayout.CENTER, this.panel);
        panel.add(cancelButton);
        panel.add(replayButton);
        cancelButton.addActionListener(new CancelAction());
        replayButton.addActionListener(new ReplayAction());
        this.setResizable(false);
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getReplayButton() {
        return replayButton;
    }

    public void setReplayButton(JButton replayButton) {
        this.replayButton = replayButton;
    }

    public class CancelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ReplayDialog.this.setEnabled(false);
            ReplayDialog.this.setVisible(false);
            ReplayDialog.this.dispose();
            LookingGUI.getInstance().disable();
            System.exit(0);
        }

    }

    public class ReplayAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().set();
            Controller.getInstance().getTurnPanel().setTurn(Controller.getInstance().getTurns());
            ReplayDialog.this.setEnabled(false);
            ReplayDialog.this.setVisible(false);
            ReplayDialog.this.dispose();
        }

    }

}
