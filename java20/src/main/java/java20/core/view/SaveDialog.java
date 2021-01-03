package java20.core.view;

import lombok.Data;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;

@Data
public class SaveDialog extends JDialog {
    private JPanel panel;
    private JButton cancelButton;
    private JButton saveButton;

    public SaveDialog() {
        super(MainGUI.getInstance().getFrame(), "Save", true);
        panel = new JPanel();
        cancelButton = new JButton("Cancel");
        saveButton = new JButton("Save");
    }

    public void go() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(550, 275, 300, 150);
        panel.setBackground(Color.WHITE);
        this.getContentPane().add(BorderLayout.CENTER, this.panel);
        panel.add(cancelButton);
        panel.add(saveButton);
        cancelButton.addActionListener(new CancelAction());
        saveButton.addActionListener(new SaveAction());
        this.setResizable(false);
        this.setVisible(true);
    }

    public class CancelAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SaveDialog.this.setEnabled(false);
            SaveDialog.this.setVisible(false);
            SaveDialog.this.dispose();
        }

    }

    public class SaveAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FileWriter fWriter = new FileWriter(new File("saved.txt"));
                FileReader fReader = new FileReader(new File("record.txt"));
                BufferedReader reader = new BufferedReader(fReader);
                String str = reader.readLine();
                while (str != null) {
                    if (!str.isBlank()) {
                        fWriter.write(str + "\n");
                    }
                    fWriter.flush();
                    str = reader.readLine();
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            SaveDialog.this.setEnabled(false);
            SaveDialog.this.setVisible(false);
            SaveDialog.this.dispose();
        }

    }

    public static void main(String[] args) {
        new SaveDialog().go();
    }
}
