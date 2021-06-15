package snake;
import java.util.Arrays;
import java.util.ArrayList;

/**
* A pályaméret beállításáért felelős gomb.
*/
public class SetFieldBtn extends Button {
    /**
    * A pályaméret, alapértelmezetten 20-as értékkel.
    */
    private static int fieldSize = 20;
    /**
    * A gomb létrehozása az ősosztály konstruktorával.
    * @param  n  a gomb neve.
    */
    public SetFieldBtn(String n) {
        super(n);
    }

    /**
    * A pályaméret beállítását megelőző menü. A menüből való kilépés az
    * utolsó ("Back") gombbal, vagy a reset gombbal elérhető.
    */
    @Override
    public void action() {
        Menu menu = new Menu(new ArrayList<Button>(Arrays.asList(
                    new ResetFieldSizeBtn("Reset"),
                    new ChangeFieldSizeBtn("Current size: " + getFieldSize()),
                    new DummyBtn("Back"))));
        try {
            int choice = menu.select();
            if (choice == 2) {
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
    * Az aktuális pályaméret lekérdezése.
    * @return  a pálya aktuális mérete.
    */
    public static int getFieldSize() {
        return fieldSize;
    }

    /**
    * A pályaméret beállítása. Ez felülírja a korábban beállított egyedi pályát.
    * @param  s  a beállítandó pályaméret
    */
    public static void setFieldSize(int s) {
        fieldSize = s;
        CustomFieldBtn.setCustom(false);
    }

    /**
    * A pályaméret alapértelmezettre állítása.
    */
    public static void resetFieldSize() {
        setFieldSize(20);
    }

    /**
    * A pályaméret beállítása nyilak segítségével, az aktuális pályaméret
    * kiírása minden módosítás után, mentés Enter-rel.
    */
    public static void changeFieldSize() {
        int current = getFieldSize();
        KeyReader keyR = new KeyReader(System.in);
        try {
            int[] keys = keyR.getKeys(3);
            while (!keyR.isEnter(keys)) {
                if (keyR.isArrow(keys)) {
                    if (keyR.arrowDir(keys) == 'U') {
                        if (current < 50) {
                            current += 1;
                            System.out.print("New size: "
                                            + current + " " + "\r");
                        }
                    } else if (keyR.arrowDir(keys) == 'D') {
                        if (current > 7) {
                            current -= 1;
                            System.out.print("New size: "
                                            + current + " " + "\r");
                        }
                    }
                }
                keys = keyR.getKeys(3);
            }
            setFieldSize(current);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
