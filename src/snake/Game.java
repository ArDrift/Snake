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
        apple = new Apple(randomSpace());
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());
        field.setCell(apple.getPos()[0], apple.getPos()[1], 'a');

    }

    public void start() throws IOException, InterruptedException {
        for (int[] b : snake.getBody()) {
            field.setCell(b[0], b[1], 's');
        }
        field.setCell(7, 9, 'o');
        Main.clearScr();
        field.print();
        KeyReader keyRead = new KeyReader(System.in);
        int[] keys = keyRead.getKeys(3);
        while (isInside(snake.getPos())) {
            if (keyRead.isArrow(keys)) {
                char dir = keyRead.arrowDir(keys);
                if (isValid(snake.newPos(dir))) {
                    if (dir != snake.getDir()) {
                        snake.setDir(dir);
                    }
                    Main.clearScr();
                    int[] tailPos = snake.getBody().get(
                                                    snake.getBody().size()-1);
                    field.setCell(tailPos[0], tailPos[1], 'e');
                    snake.move(dir);
                    if (field.getCell(snake.getPos()[0],
                                    snake.getPos()[1]).getType() == 'a') {
                        apple.setPos(randomSpace());
                        field.setCell(
                                    apple.getPos()[0], apple.getPos()[1], 'a');
                        }
                    updateField();
                    field.print();
                }
            }
            keys = keyRead.getKeys(3);
            if (keys[0] == 13) {
                break;
            }
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
