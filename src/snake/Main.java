package snake;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Button> btns = new ArrayList<>(Arrays.asList(
            new NewGameBtn("New game"),
            new LoadSaveBtn("Load save"),
            new SetFieldBtn("Set field size"),
            new CustomFieldBtn("Create custom field"),
            new HighScoresBtn("Leaderboard"),
            new DummyBtn("Exit")));

        Menu menu = new Menu(btns);
        setRaw(true);
        int choice = menu.select();
        while (choice != menu.getSize()-1) {
            choice = menu.select();
        }

        setRaw(false);

    }

    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    public static void clearScr() throws IOException, InterruptedException {
        // https://stackoverflow.com/questions/2979383/java-clear-the-console
        if (isWindows()) {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        try {
            printLogo(new File("logos", "logo.txt"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cursorToZero() throws IOException, InterruptedException {
        if (isWindows()) {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H");
            System.out.flush();
        }
        try {
            printLogo(new File("logos", "logo.txt"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void setRaw(boolean raw) throws IOException, InterruptedException {
        // https://stackoverflow.com/questions/1066318/how-to-read-a-single-char-from-the-console-in-java-as-the-user-types-it/1066339#6876253
        if (!isWindows()) {
            String[] rawCmd = {"/bin/sh", "-c", "stty raw < /dev/tty"};
            String[] echoCmd = {"/bin/sh", "-c", "stty -echo < /dev/tty"};
            if (!raw) {
                rawCmd[2] = "stty cooked < /dev/tty";
                echoCmd[2] = "stty echo < /dev/tty";
            }
            Runtime.getRuntime().exec(rawCmd).waitFor();
            Runtime.getRuntime().exec(echoCmd).waitFor();
        }
    }

    public static void printLogo(File logo) throws IOException, FileNotFoundException {
        if (logo.exists()) {
            BufferedReader l = new BufferedReader(new FileReader(logo));
            String line = l.readLine();
            while (line != null) {
                System.out.println("\r" + line);
                line = l.readLine();
            }
            l.close();
        }
    }
}
