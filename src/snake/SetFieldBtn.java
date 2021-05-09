package snake;
import java.util.Arrays;
import java.util.ArrayList;

public class SetFieldBtn extends Button {
    private static int fieldSize = 20;
    public SetFieldBtn(String n) {
        super(n);
    }

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

    public static int getFieldSize() {
        return fieldSize;
    }

    public static void setFieldSize(int s) {
        fieldSize = s;
        CustomFieldBtn.setCustom(false);
    }

    public static void resetFieldSize() {
        setFieldSize(20);
    }

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
