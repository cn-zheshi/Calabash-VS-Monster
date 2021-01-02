package java20.core.view;

import javax.swing.JButton;
import javax.swing.JFrame;

import java20.core.Controller;
import java20.util.GameType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {
    private JFrame frame;
    private JButton playButton;
    private JButton lookingButton;

    public static void main(String[] args) {
        new MainMenu().go();
    }

    public MainMenu() {
        frame = new JFrame();
        playButton = new JButton("开始游戏");
        lookingButton = new JButton("查看录像");
    }

    public void go() {
        frame.setTitle("Calabash VS Monster");
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(playButton);
        frame.getContentPane().add(lookingButton);
        playButton.addActionListener(new PlayButton());
        playButton.setSize(200, 80);
        playButton.setLocation(300, 300);
        lookingButton.addActionListener(new LookingButton());
        lookingButton.setSize(200, 80);
        lookingButton.setLocation(300, 400);
        frame.setSize(800, 635);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return this.frame;
    }
    public class PlayButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().setGameType(GameType.Playing);
            frame.setEnabled(false);
            frame.setVisible(false);
        }

    }

    public class LookingButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().setGameType(GameType.Looking);
            frame.setEnabled(false);
            frame.setVisible(false);
        }

    }
}
