package java20;

import java20.core.Controller;
import java20.core.view.MainMenu;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.go();
        while (mainMenu.getFrame().isEnabled()) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Controller.getInstance().set();
        Controller.getInstance().go();
    }
}
