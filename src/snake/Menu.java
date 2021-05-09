package snake;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Menu {
    private ArrayList<Button> buttons;

    public Menu(ArrayList<Button> btns) {
        buttons = btns;
    }

    public void print() {
        for (Button b : buttons) {
            if (b.isActive()) {
                System.out.println("\r" + ">> " + b + " <<");
            } else {
                System.out.println("\r" + b + "      ");
            }
        }
        System.out.println("");
        System.out.println("Control: ↑ ↓ → ← ENTER ESC");
        System.out.print("\r");
    }

    public Button getButton(int i) {
        return buttons.get(i);
    }

    public int getSize() {
        return buttons.size();
    }

    public int getActive() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    public int select() throws IOException, InterruptedException {
        Main.clearScr();
        int choice = getActive();
        if (choice == -1) {
            getButton(0).setActive(true);
            choice = 0;
        }
        print();
        KeyReader keyRead = new KeyReader(System.in);
        int[] keys = keyRead.getKeys(3);
        while (!keyRead.isEnter(keys)) {
            if (keyRead.isArrow(keys)) {
                getButton(choice).setActive(false);
                switch (keyRead.arrowDir(keys)) {
                    case 'U':
                        if (choice != 0) {
                            choice -= 1;
                        } else {
                            choice = getSize()-1;
                        }
                        break;
                    case 'D':
                        if (choice != getSize()-1) {
                            choice += 1;
                        } else {
                            choice = 0;
                        }
                        break;
                }
            }
            getButton(choice).setActive(true);
            Main.cursorToZero();
            print();
            keys = keyRead.getKeys(3);
        }
        getButton(choice).action();
        Main.clearScr();
        return choice;
        //System.out.println("Run: " + getButton(choice) + "!");
    }
}
