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
                System.out.println("\r" + b);
            }
        }
    }

    public Button getButton(int i) {
        return buttons.get(i);
    }

    public int getSize() {
        return buttons.size();
    }

    public void select() throws IOException, InterruptedException {
        Main.clearScr();
        int choice = 0;
        getButton(choice).setActive(true);
        print();
        BufferedReader in =
            new BufferedReader(new InputStreamReader(System.in));
        char[] keys = new char[3];
        in.read(keys, 0, 3);
        while ((int)keys[0] != 13) {
            if ((int)keys[0] == 27 && (int)keys[1] == 91) {
                getButton(choice).setActive(false);
                switch ((int)keys[2]) {
                    // arrow up: 27-91-65
                    case 65:
                        if (choice != 0) {
                            choice -= 1;
                        } else {
                            choice = getSize()-1;
                        }
                        break;
                    // arrow down: 27-91-66
                    case 66:
                        if (choice != getSize()-1) {
                            choice += 1;
                        } else {
                            choice = 0;
                        }
                        break;
                }
            }
            getButton(choice).setActive(true);
            Main.clearScr();
            print();
            in.read(keys, 0, 3);
        }
        getButton(choice).action();
        System.out.println("Run: " + getButton(choice) + "!");
    }
}
