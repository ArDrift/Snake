package snake;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ArrayList<Button> btns = new ArrayList<>(Arrays.asList(
            new NewGameBtn("New game"),
            new LoadSaveBtn("Load save"),
            new SetFieldBtn("Set field size"),
            new CustomFieldBtn("Create custom field"),
            new ExitBtn("Exit")));

        Menu menu = new Menu(btns);
        btns.get(1).setActive(true);

        menu.print();

        Field field = new Field(2, 5);
        field.setCell(1, 2, 's');
        field.setCell(0, 4, 's');
        field.print();

    }

    public static void clearScr() throws IOException, InterruptedException {
        // https://stackoverflow.com/questions/2979383/java-clear-the-console
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
