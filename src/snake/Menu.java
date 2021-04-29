package snake;
import java.util.ArrayList;

public class Menu {
    private ArrayList<Button> buttons;

    public Menu(ArrayList<Button> btns) {
        buttons = btns;
    }

    public void print() {
        for (Button b : buttons) {
            if (b.isActive()) {
                System.out.println(">> " + b + " <<");
            } else {
                System.out.println(b);
            }
        }
    }
}
