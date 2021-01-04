package java20.core.view;

import java20.core.Controller;
import java20.util.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenu extends JFrame {

    private BackPanel backPanel;

    public MainMenu() {
        this.setTitle("Calabash VS Monster");
        this.getContentPane().setLayout(null);
        File f = new File(this.getClass().getResource("/").getPath());
        String url = f.getPath() + "/front.jpg";
        ImageIcon image = new ImageIcon(url);

        backPanel = new BackPanel(image.getImage());
        backPanel.setBounds(0, 0, 800, 635);


        JButton playButton = new JButton("开始游戏");
        JButton lookingButton = new JButton("查看录像");

        playButton.addActionListener(new PlayButton());
        playButton.setSize(200, 80);
        playButton.setLocation(300, 300);
        lookingButton.addActionListener(new LookingButton());
        lookingButton.setSize(200, 80);
        lookingButton.setLocation(300, 400);

        this.getContentPane().add(playButton);
        this.getContentPane().add(lookingButton);
        this.getContentPane().add(backPanel);

        this.setSize(800, 635);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public static void main(String[] args) {
        new MainMenu().go();
    }

    public void go() {
        this.setVisible(true);
    }

    public JFrame getFrame() {
        return this;
    }

    public class PlayButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().setGameType(GameType.Playing);
            MainMenu.this.setEnabled(false);
            MainMenu.this.setVisible(false);
        }

    }

    public class LookingButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().setGameType(GameType.Looking);
            MainMenu.this.setEnabled(false);
            MainMenu.this.setVisible(false);
        }

    }

    public static class BackPanel extends JPanel {
        private Image image;

        public BackPanel(Image image) {
            this.image = image;
            this.setOpaque(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}


