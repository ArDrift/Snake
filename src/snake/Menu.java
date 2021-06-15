package snake;
import java.util.ArrayList;
import java.io.IOException;

/**
* A programban több helyen megtalálható menükhöz használt osztály.
*/
public class Menu {
    /**
    * A menüből elérhető gombok.
    */
    private ArrayList<Button> buttons;

    /**
    * Menü létrehozása a gombok megadásával.
    * @param  btns  a menüt kitöltő gombok listája.
    */
    public Menu(ArrayList<Button> btns) {
        buttons = btns;
    }

    /**
    * A menü kiírása a kimenetre, az éppen kiválasztott gombot jelölve,
    * az irányításhoz szükséges gombokkal együtt.
    */
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

    /**
    * Az adott indexű gomb lekérése a menüből.
    * @param  i  a gomblistából lekérendő gomb indexe.
    * @return  a lekért gomb indexe.
    */
    public Button getButton(int i) {
        return buttons.get(i);
    }

    /**
    * A menüben használt gomblista méretének lekérése.
    * @return  a gomblista mérete.
    */
    public int getSize() {
        return buttons.size();
    }

    /**
    * A menüben éppen aktív gomb indexének lekérése.
    * @return  a kiválasztott gomb indexe, vagy -1, ha nincs ilyen.
    */
    public int getActive() {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isActive()) {
                return i;
            }
        }
        return -1;
    }

    /**
    * A menü elindítása, irányítás átadása a felhasználónak, Enter lenyomásánál
    * pedig a választott gombhoz tartozó program futtatása. A billentyűk
    * között a fel-le nyilakkal válthat a felhasználó.
    * @return  a felhasználó által végül kiválasztott gomb indexe.
    */
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
    }
}
