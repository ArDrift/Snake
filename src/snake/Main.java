package snake;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
* A főprogram indításáért felelős osztály.
*/
public class Main {

    /**
    * A main függvény, ami felépíti a főmenüt, és beállítja a játékhoz
    * szükséges terminál beállításokat.
    */
    private static int originalConsoleMode;
    private static Kernel32 kernel32;
    private static Pointer consoleHandle;
    private static boolean initDone;
    private static boolean stdinIsConsole;
    private static boolean consoleModeAltered;

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

    /**
    * @return igaz, ha a programot futtató rendszer windows, hamis, ha nem.
    */
    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    /**
    * A konzol törlése.
    */
    public static void clearScr() throws IOException {
        // https://stackoverflow.com/questions/2979383/java-clear-the-console
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            printLogo(new File("logos", "logo.txt"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
    * A kurzor 0 pozícióba helyezése.
    */
    public static void cursorToZero() throws IOException {
        System.out.print("\033[H");
        System.out.flush();
        try {
            printLogo(new File("logos", "logo.txt"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
    * A konzol raw (nyers) mode-ba állítása, ha a rendszer nem Windows.
    * Ez szükséges a játék működéséhez, hogy a program azonnal megkapjon minden
    * leütött billentyűt bemenetként.
    */
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
        } else {
            if (raw) {
                try {
                    initWindows();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
                consoleModeAltered = true;
                setConsoleMode(consoleHandle, originalConsoleMode & ~Kernel32Defs.ENABLE_PROCESSED_INPUT);
                enableVirtTermSeqs(true);
            } else {
                consoleModeAltered = false;
                resetConsoleModeWindows();
            }
        }
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                shutdownHook();
            }
        });
    }

    private static void shutdownHook() {
        try {
            resetConsoleModeWindows();
        } catch (Exception e) {}
        }

    private static interface Kernel32 extends Library {
        int GetConsoleMode (Pointer hConsoleHandle, IntByReference lpMode);
        int SetConsoleMode (Pointer hConsoleHandle, int dwMode);
        Pointer GetStdHandle (int nStdHandle);
    }

    public static void enableVirtTermSeqs(boolean e) {
        if (e) {
            try {
                setConsoleMode(getStdOutputHandle(),
                    Kernel32Defs.ENABLE_VIRTUAL_TERMINAL_PROCESSING);
            } catch (Exception err) {
                System.err.println(err.getMessage());
                err.printStackTrace();
            }
        } else {
            try {
                resetConsoleModeWindows();
            } catch (Exception err) {
                System.err.println(err.getMessage());
                err.printStackTrace();
            }
        }
    }

    private static int getConsoleMode (Pointer handle) throws IOException {
        IntByReference mode = new IntByReference();
        int rc = kernel32.GetConsoleMode(handle, mode);
        if (rc == 0) {
            throw new IOException("GetConsoleMode() failed.");
        }
        return mode.getValue();
    }

    private static void setConsoleMode (Pointer handle, int mode) throws IOException {
        int rc = kernel32.SetConsoleMode(handle, mode);
        if (rc == 0) {
            throw new IOException("SetConsoleMode() failed.");
        }
    }

    private static void resetConsoleModeWindows() throws IOException {
        if (!initDone || !stdinIsConsole || !consoleModeAltered) {
            return;
        }
        setConsoleMode(consoleHandle, originalConsoleMode);
        consoleModeAltered = false;
    }

    private static class Kernel32Defs {
        static final int  STD_INPUT_HANDLE       = -10;
        static final int  STD_OUTPUT_HANDLE      = -11;
        static final long INVALID_HANDLE_VALUE   = (Native.POINTER_SIZE == 8) ? -1 : 0xFFFFFFFFL;
        static final int  ENABLE_PROCESSED_INPUT = 0x0001;
        static final int  ENABLE_LINE_INPUT      = 0x0002;
        static final int  ENABLE_ECHO_INPUT      = 0x0004;
        static final int  ENABLE_WINDOW_INPUT    = 0x0008;
        static final int  ENABLE_VIRTUAL_TERMINAL_PROCESSING = 0x0004;
    }

    private static synchronized void initWindows() throws IOException {
        if (initDone) {
            return;
        }
        kernel32 = Native.load("kernel32", Kernel32.class);
        try {
            consoleHandle = getStdInputHandle();
            originalConsoleMode = getConsoleMode(consoleHandle);
            stdinIsConsole = true;
        } catch (IOException e) {
            stdinIsConsole = false;
            if (stdinIsConsole) {
                registerShutdownHook();
            }
        }
        initDone = true;
    }

    private static Pointer getStdInputHandle() throws IOException {
        Pointer handle = kernel32.GetStdHandle(Kernel32Defs.STD_INPUT_HANDLE);
        if (Pointer.nativeValue(handle) == 0 || Pointer.nativeValue(handle) == Kernel32Defs.INVALID_HANDLE_VALUE) {
            throw new IOException("GetStdHandle(STD_INPUT_HANDLE) failed.");
        }
        return handle;
    }

    private static Pointer getStdOutputHandle() throws IOException {
        Pointer handle = kernel32.GetStdHandle(Kernel32Defs.STD_OUTPUT_HANDLE);
        if (Pointer.nativeValue(handle) == 0 || Pointer.nativeValue(handle) == Kernel32Defs.INVALID_HANDLE_VALUE) {
            throw new IOException("GetStdHandle(STD_OUTPUT_HANDLE) failed.");
        }
        return handle;
    }

    /**
    * A paraméterként megadott file kiírása.
    * @param  logo a file aminek tartalmát ki szeretnénk írni a kimenetre
    */
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
