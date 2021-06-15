package snake;
import java.io.IOException;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
* A játék közbeni, és azokhoz közvetlenül kapcsolódó eseményekért felelős
* osztály, ez fogja össze a játék működéséhez szükséges elemeket.
*/
public class Game {

    /**
    * A játékban használt pálya.
    */
    private Field field;

    /**
    * A játék aktuális kígyója.
    */
    private Snake snake;

    /**
    * Alma a játékban.
    */
    private Apple apple;

    /**
    * A játékbeli pontszám.
    */
    private int pts;

    /**
    * Játék létrehozása megadott pályával, kígyóval, és pontszámmal.
    * @param  f  a pálya.
    * @param  s  a kígyó.
    * @param  p  a pontszám.
    */
    public Game(Field f, Snake s, int p) {
        field = f;
        snake = s;
        pts = p;
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        apple = new Apple(randomSpace());
        field.setCell(apple.getPos()[0], apple.getPos()[1], 'a');

    }

    /**
    * Játék létrehozása a fentiek szerint, plusz egy előre megadott almával.
    * @param  f  a pálya.
    * @param  s  a kígyó.
    * @param  p  a pontszám.
    * @param  a  az alma.
    */
    public Game(Field f, Snake s, int p, Apple a) {
        field = f;
        snake = s;
        pts = p;
        apple = a;
    }

    /**
    * Mentés betöltésénél használt üres játék létrehozása, amit később
    * felülírunk a betöltött mentéssel.
    */
    public Game() {
    }

    /**
    * A játék elindítása, a pálya feltöltésével a kígyó testrészei szerint,
    * a pálya, pontszám, és irányításhoz használható gombok kiíratásával.
    * Játék közben vizsgáljuk a kígyó pozícióját, hogy a pályán belül legyen,
    * illetve ütközésnél leállítjuk a játékot. Ez kezeli még a kígyó
    * növését, az alma újragenerálását is, és a pause menübe is innen lépünk be.
    * Ha pedig a pontszámunk kellően magas, a mentés folyamatát is elindítja.
    */
    public void start() throws IOException, InterruptedException {
        for (int i = 0; i < snake.getBody().size(); i++) {
            int[] b = snake.getBody().get(i);
            field.setCell(b[0], b[1], 's');
            field.getCell(b[0], b[1]).setData(i);
        }
        Main.cursorToZero();
        field.print(pts, "PTS:");
        System.out.println("");
        System.out.println("       Control: ↑ ↓ → ← ENTER ESC");
        KeyReader keyRead = new KeyReader(System.in);
        int[] keys = new int[] {0, 0, 0};
        if (keyRead.ready() && keyRead.isArrow(keys)) {
            keys = keyRead.getKeys(3);
        }
        while (isInside(snake.getPos())) {
            char newDir = snake.getDir();
            if (keyRead.isArrow(keys)
                && keyRead.arrowDir(keys) != opposite(snake.getDir())) {
                newDir = keyRead.arrowDir(keys);
            }
            snake.setDir(newDir);
            if (isValid(snake.newPos(snake.getDir()))) {
                Main.cursorToZero();
                int[] tailPos = snake.getTailPos();
                field.setCell(tailPos[0], tailPos[1], 'e');
                snake.move(snake.getDir());
                if (field.getCell(snake.getPos()[0],
                                snake.getPos()[1]).getType() == 'a') {
                    field.setCell(tailPos[0], tailPos[1], 's');
                    field.getCell(tailPos[0], tailPos[1]).setData(
                                                        snake.getLength()-1);
                    snake.grow();
                    addPts(1);
                    apple.setPos(randomSpace());
                    field.setCell(
                                apple.getPos()[0], apple.getPos()[1], 'a');
                    }
                updateField();
                field.print(pts, "PTS:");
            } else if (snake.getDir() != opposite(newDir)) {
                break;
            }
            Thread.sleep(100);
            keys[0] = 0; keys[1] = 0; keys[2] = 0;
            if (keyRead.ready()) {
                keys = keyRead.getKeys(3);
            }
            if (keys[0] == 27 && keys[1] == 0 && keys[2] == 0) {
                if (pause(this)) {
                    break;
                }
            }
        }
        cleanupField();
        Main.clearScr();
        Main.printLogo(new File("logos", "game_over.txt"));
        Thread.sleep(1000);
        if (isHighScore(pts)) {
            System.out.println("\r" + "Congratulations, you have "
                               + "earned a place on the leaderboard!");
            Thread.sleep(1000);
            System.out.println("\r" + "Enter your name: " + "\r");
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(System.in));
            Main.setRaw(false);
            String name = in.readLine();
            Main.setRaw(true);
            saveHighScore(new HighScore(name, pts));
        }
    }

    /**
    * Visszaadja a paraméterként kapott irány ellenkezőjét.
    * @param  dir  karakterként megadott irány, angol kezdőbetű szerint.
    * @return  az irány ellentéte, hasonló módon megadva.
    */
    public char opposite(char dir) {
        switch (dir) {
            case 'U': return 'D';
            case 'D': return 'U';
            case 'R': return 'L';
            case 'L': return 'R';
            default: return 'X';
        }
    }

    /**
    * A pálya frissítése, a kígyó pozíciója, és testrészei szerint.
    */
    public void updateField() {
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        for (int b = 0; b < snake.getBody().size(); b++) {
            int[] bPos = snake.getBody().get(b);
            if (field.getCell(bPos[0], bPos[1]).getType() != 'o') {
                field.setCell(bPos[0], bPos[1], 's');
                field.getCell(bPos[0], bPos[1]).setData(b);
            }
        }
    }

    /**
    * A pálya "feltörlése", hogy a játék végén a pályán ne maradjon semmi,
    * akadályon kívül.
    */
    public void cleanupField() {
        for (int y = 0; y < field.getSize()[1]; y++) {
            for (int x = 0; x < field.getSize()[0]; x++) {
                if (field.getCell(x, y).getType() != 'o') {
                    field.getCell(x, y).setType('e');
                }
            }
        }
    }

    /**
    * Annak vizsgálata, hogy egy adott koordináta a pályán belül van-e.
    * @return  igaz, ha a koordináta a pályán belül van, különben hamis.
    */
    public boolean isInside(int[] coord) {
        int[] size = field.getSize();
        return 0 <= coord[0] && coord[0] < size[0]
               && 0 <= coord[1] && coord[1] < size[1];
    }

    /**
    * Annak vizsgálata, hogy a megadott koordináta a pályán belül van-e,
    * illetve hogy az adott koordináta üres, vagy alma, tehát mozoghat oda a
    * kígyó.
    * @return  igaz, ha a kígyó mozoghat a megadott helyre, különben hamis.
    */
    public boolean isValid(int[] coord) {
        return isInside(coord)
               && (field.getCell(coord[0], coord[1]).getType() == 'e'
               || field.getCell(coord[0], coord[1]).getType() == 'a');
    }

    /**
    * Véletlenszerű üres Cellájú koordináta visszaadása.
    * @return  egy véletlenszerű üres cella x és y koordinátája egy 2 elemű int
    * tömbben.
    */
    public int[] randomSpace() {
        Random r = new Random();
        int x = r.nextInt(field.getSize()[0]);
        int y = r.nextInt(field.getSize()[1]);
        while (field.getCell(x, y).getType() != 'e') {
            x = r.nextInt(field.getSize()[0]);
            y = r.nextInt(field.getSize()[1]);
        }
        return new int [] {x, y};
    }

    /**
    * Paraméterként megadott pontszám hozzáadása a meglévőhöz.
    * @param  d  a meglévőhöz hozzáadni kívánt pontszám (int)
    */
    public void addPts(int d) {
        pts += d;
    }

    /**
    * Annak eldöntése, hogy a paraméterként megadott pontszám rekord lett-e.
    * @param  pts  pontszám, amiről eldöntjük, hogy rekord-e (int)
    * @return  igaz, ha benne van a legnagyobb 5 eddigi pontszámban, vagy még
    * kevesebb, mint 5 rekord van, különben hamis.
    */
    public boolean isHighScore(int pts) throws IOException {
        ArrayList<HighScore> highs = HighScoresBtn.getHighScores();
        return (highs.size() < 5 || pts > highs.get(highs.size()-1).getPts());
    }

    /**
    * A paraméterként megadott rekord elmentése a dicsőséglistára,
    * a highscores.txt-be.
    * @param  h  HighScore típusú rekord
    */
    public void saveHighScore(HighScore h) throws IOException {
        ArrayList<HighScore> highs = HighScoresBtn.getHighScores();
        highs.add(h);
        highs.sort(Collections.reverseOrder(new PtsComparator()));
        if (highs.size() > 5) {
            highs.remove(highs.get(highs.size()-1));
        }
        File hs = new File("highscores.txt");
        if (!hs.exists()) {
            hs.createNewFile();
        }
        PrintWriter wt = new PrintWriter(
                         new BufferedWriter(new FileWriter(hs)));
        for (HighScore x : highs) {
            wt.println(x.getName());
            wt.println(x.getPts());
            wt.println(x.getDate());
            if (x != highs.get(highs.size()-1)) {
                wt.println("");
            }
        }
        wt.close();
    }

    /**
    * A játékállás elmentése a save.txt file-ba, beleírjuk a mátrixot, illetve
    * a pontszámot.
    */
    public void saveGame() throws IOException {
        File save = new File("save.txt");
        if (!save.exists()) {
            save.createNewFile();
        }
        PrintWriter wt = new PrintWriter(
                         new BufferedWriter(new FileWriter("save.txt")));

        field.printMatrix(wt);
        wt.println("");
        wt.println(pts);

        wt.close();
    }

    /**
    * A mentett játékállás betöltése a save.txt file-ból.
    * @return  Game típusú játék, ami már tartalmazza
    * a betöltött attribútumokat, vagy az alapértelmezetteket, amennyiben
    * nem található mentés.
    */
    public Game loadGame() throws IOException, InterruptedException {
        File save = new File("save.txt");
        int pts = 0;
        if (save.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(save));
            String line = in.readLine();
            ArrayList<ArrayList<String>> matrix = new ArrayList<>();
            while (line != null) {
                ArrayList<String> row = new ArrayList<>();
                for (String c: line.split(" ")) {
                    row.add(c);
                }
                matrix.add(row);
                line = in.readLine();
                if (line != null && line.equals("")) {
                    line = in.readLine();
                    pts = Integer.parseInt(line);
                    line = in.readLine();
                }
            }
            in.close();

            Cell[][] arrayMatrix = new Cell[matrix.size()]
                                           [matrix.get(0).size()];
            char currentChar; char snakeD = ' '; int[] snakeP = new int[2];
            int[] aPos = new int[2]; int currentData = -1;
            ArrayList<int[]> snakeB = new ArrayList<>();
            for (int y = 0; y < matrix.size(); y++) {
                for (int x = 0; x < matrix.get(y).size(); x++) {
                    if (Character.isDigit(matrix.get(y).get(x).charAt(0))) {
                        currentChar = 's';
                        currentData = Integer.parseInt(matrix.get(y).get(x));
                        arrayMatrix[y][x] = new Cell(currentChar, currentData);

                    } else {
                        currentChar = matrix.get(y).get(x).charAt(0);
                        arrayMatrix[y][x] = new Cell(currentChar);
                    }
                    if (currentChar == 'U' || currentChar == 'D'
                        || currentChar == 'L' || currentChar == 'R') {
                            snakeD = currentChar;
                            snakeP[0] = x; snakeP[1] = y;
                    } else if (currentChar == 's') {
                        snakeB.add(new int[] {x, y, currentData});
                    } else if (currentChar == 'a') {
                        aPos[0] = x; aPos[1] = y;
                    }
                }
            }
            snakeB.sort(new SnakeDataComparator());
            ArrayList<int[]> snakeBody = new ArrayList<>();
            for (int[] s : snakeB) {
                snakeBody.add(new int[] {s[0], s[1]});
            }
            return new Game(new Field(arrayMatrix),
                            new Snake(snakeP[0], snakeP[1], snakeD, snakeBody),
                            pts, new Apple(aPos));
        } else {
            return new Game(new Field(20, 20), new Snake(10, 10, 'U'), 0);
        }
    }

    /**
    * A paraméterként kapott, folyamatban lévő játék megállítása, menübe lépés.
    * Innen folytatható, illetve menthető a játék,
    * de ki is léphetünk a főmenübe.
    * @param  g  Game típusú játék, amit megállítunk
    * @return  igaz, ha kilépünk a játékból, hamis, ha folytatjuk azt.
    */
    public boolean pause(Game g) {
        boolean exit = false;
        Menu menu = new Menu(new ArrayList<Button>(Arrays.asList(
                                            new DummyBtn("Resume"),
                                            new SaveGameBtn("Save game", g),
                                            new DummyBtn("Exit"))));
        try {
            int choice = menu.select();
            while (choice == 1) {
                Main.setRaw(false);
                System.out.println("Game was saved successfully");
                Main.setRaw(true);
                choice = menu.select();
            }
            if (choice == 2) {
                exit = true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return exit;
    }
}
