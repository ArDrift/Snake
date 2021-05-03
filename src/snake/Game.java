package snake;
import java.io.IOException;
import java.util.Random;

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

    public void start() throws IOException, InterruptedException {
        for (int[] b : snake.getBody()) {
            field.setCell(b[0], b[1], 's');
        }
        Main.clearScr();
        field.print();
        KeyReader keyRead = new KeyReader(System.in);
        int[] keys = new int[] {0, 0, 0};
        if (keyRead.ready()) {
            keys = keyRead.getKeys(3);
        }
        while (isInside(snake.getPos())) {
            char keyDir = snake.getDir();
            if (!(keys[0] == 0 && keys[1] == 0 && keys[2] == 0)) {
                keyDir = keyRead.arrowDir(keys);
            }
            if (isValid(snake.newPos(snake.getDir()))
                && snake.getDir() != opposite(keyDir)) {
                if (keyDir != snake.getDir()) {
                    snake.setDir(keyDir);
                }
                Main.clearScr();
                int[] tailPos = snake.getTailPos();
                field.setCell(tailPos[0], tailPos[1], 'e');
                snake.move(snake.getDir());
                if (field.getCell(snake.getPos()[0],
                                snake.getPos()[1]).getType() == 'a') {
                    field.setCell(tailPos[0], tailPos[1], 's');
                    snake.grow();
                    apple.setPos(randomSpace());
                    field.setCell(
                                apple.getPos()[0], apple.getPos()[1], 'a');
                    }
                updateField();
                field.print();
            } else if (snake.getDir() != opposite(keyDir)) {
                break;
            }
            Thread.sleep(100);
            if (keyRead.ready()) {
                keys = keyRead.getKeys(3);
            }
        }
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
}
