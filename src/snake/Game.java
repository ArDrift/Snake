package snake;
import java.io.IOException;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.Collections;

public class Game {
    private Field field;
    private Snake snake;
    private Apple apple;
    private int pts;

    public Game(Field f, Snake s, int p) {
        field = f;
        snake = s;
        pts = p;
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        apple = new Apple(randomSpace());
        field.setCell(apple.getPos()[0], apple.getPos()[1], 'a');

    }

    public Game(Field f, Snake s, int p, Apple a) {
        field = f;
        snake = s;
        pts = p;
        apple = a;
    }

    public Game() {
    }

    public void setDefaults() {
        field = new Field(20, 20);
        snake = new Snake(randomSpace()[0], randomSpace()[1], 'U');
        pts = 0;
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        apple = new Apple(randomSpace());
        field.setCell(apple.getPos()[0], apple.getPos()[1], 'a');
    }

    public void start() throws IOException, InterruptedException {
        for (int[] b : snake.getBody()) {
            field.setCell(b[0], b[1], 's');
        }
        Main.clearScr();
        field.print(pts);
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
                Main.clearScr();
                int[] tailPos = snake.getTailPos();
                field.setCell(tailPos[0], tailPos[1], 'e');
                snake.move(snake.getDir());
                if (field.getCell(snake.getPos()[0],
                                snake.getPos()[1]).getType() == 'a') {
                    field.setCell(tailPos[0], tailPos[1], 's');
                    snake.grow();
                    addPts(1);
                    apple.setPos(randomSpace());
                    field.setCell(
                                apple.getPos()[0], apple.getPos()[1], 'a');
                    }
                updateField();
                field.print(pts);
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
        saveHighScore(new HighScore("ArDrift", pts));
    }

    public char opposite(char dir) {
        switch (dir) {
            case 'U': return 'D';
            case 'D': return 'U';
            case 'R': return 'L';
            case 'L': return 'R';
            default: return 'X';
        }
    }

    public void updateField() {
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        for (int[] bPos: snake.getBody()) {
            if (field.getCell(bPos[0], bPos[1]).getType() != 'o') {
                field.getCell(bPos[0], bPos[1]).setType('s');
            }
        }
    }

    public boolean isInside(int[] coord) {
        int[] size = field.getSize();
        return 0 <= coord[0] && coord[0] < size[0]
               && 0 <= coord[1] && coord[1] < size[1];
    }

    public boolean isValid(int[] coord) {
        return isInside(coord)
               && (field.getCell(coord[0], coord[1]).getType() == 'e'
               || field.getCell(coord[0], coord[1]).getType() == 'a');
    }

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

    public void addPts(int d) {
        pts += d;
    }

    public ArrayList<HighScore> getHighScores() throws IOException {
        ArrayList<HighScore> highs = new ArrayList<>();
        File hs = new File("highscores.txt");
        if (!hs.exists()) {
            return highs;
        } else {
            BufferedReader in = new BufferedReader(
                                new FileReader("highscores.txt"));
            String line = in.readLine();
            while (line != null) {
                String name = line; line = in.readLine();
                int pts = Integer.parseInt(line); line = in.readLine();
                LocalDateTime date = LocalDateTime.parse(line);
                highs.add(new HighScore(name, pts, date));
                line = in.readLine();
                if (line != null && line.equals("")) {
                    line = in.readLine();
                }
            }
            return highs;
        }
    }

    public void saveHighScore(HighScore h) throws IOException {
        ArrayList<HighScore> highs = getHighScores();
        if (highs.size() < 5
            || h.getPts() > highs.get(highs.size()-1).getPts()) {
            highs.add(h);
            highs.sort(Collections.reverseOrder(new PtsComparator()));
            if (highs.size() > 5) {
                highs.remove(highs.get(highs.size()-1));
            }
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

    public Game loadGame() throws IOException, InterruptedException {
        File save = new File("save.txt");
        Game game; int pts = 0;
        if (save.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(save));
            String line = in.readLine();
            ArrayList<ArrayList<Character>> matrix = new ArrayList<>();
            while (line != null) {
                ArrayList<Character> row = new ArrayList<>();
                for (String c: line.split(" ")) {
                    row.add(c.charAt(0));
                }
                matrix.add(row);
                line = in.readLine();
                if (line != null && line.equals("")) {
                    line = in.readLine();
                    pts = Integer.parseInt(line);
                    line = in.readLine();
                }
            }

            Cell[][] arrayMatrix = new Cell[matrix.size()]
                                           [matrix.get(0).size()];
            char currentChar; char snakeD = ' '; int[] snakeP = new int[2];
            int[] aPos = new int[2];
            ArrayList<int[]> snakeB = new ArrayList<>();
            for (int y = 0; y < matrix.size(); y++) {
                for (int x = 0; x < matrix.get(y).size(); x++) {
                    currentChar = matrix.get(y).get(x);
                    arrayMatrix[y][x] = new Cell(currentChar);
                    if (currentChar == 'U' || currentChar == 'D'
                        || currentChar == 'L' || currentChar == 'R') {
                            snakeD = currentChar;
                            snakeP[0] = x; snakeP[1] = y;
                    } else if (currentChar == 's') {
                        snakeB.add(new int[] {x, y});
                    } else if (currentChar == 'a') {
                        aPos[0] = x; aPos[1] = y;
                    }
                }
            }
            return new Game(new Field(arrayMatrix),
                            new Snake(snakeP[0], snakeP[1], snakeD, snakeB),
                            pts, new Apple(aPos));
        } else {
            return new Game(new Field(20, 20), new Snake(10, 10, 'U'), 0);
        }
    }

    public boolean pause(Game g) {
        boolean exit = false;
        Menu menu = new Menu(new ArrayList<Button>(Arrays.asList(
                                            new ResumeBtn("Resume"),
                                            new SaveGameBtn("Save game", g),
                                            new ExitBtn("Exit"))));
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
