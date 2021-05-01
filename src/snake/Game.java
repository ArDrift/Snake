package snake;
import java.io.IOException;

public class Game {
    private Field field;
    private Snake snake;
    private int pts;

    public Game(Field f, Snake s, int p) {
        field = f;
        snake = s;
        pts = p;
        field.setCell(snake.getPos()[0], snake.getPos()[1], snake.getDir());

    }

    public void start() throws IOException, InterruptedException {
        for (int[] b : snake.getBody()) {
            field.setCell(b[0], b[1], 's');
        }
        field.setCell(7, 9, 'o');
        field.setCell(1, 6, 'a');
        Main.clearScr();
        field.print();
        KeyReader keyRead = new KeyReader(System.in);
        int[] keys = keyRead.getKeys(3);
        while (isInside(snake.getPos())) {
            if (keyRead.isArrow(keys)) {
                char dir = keyRead.arrowDir(keys);
                if (isInside(snake.newPos(dir))) {
                    if (dir != snake.getDir()) {
                        snake.setDir(dir);
                    }
                    Main.clearScr();
                    int[] tailPos = snake.getBody().get(
                                                    snake.getBody().size()-1);
                    field.setCell(tailPos[0], tailPos[1], 'e');
                    snake.move(dir);
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
            if (field.getMatrix()[bPos[0]][bPos[1]].getType() != 'o') {
                field.getMatrix()[bPos[0]][bPos[1]].setType('s');
            }
        }
    }

    public boolean isInside(int[] coord) {
        int[] size = field.getSize();
        return 0 <= coord[0] && coord[0] < size[0]
               && 0 <= coord[1] && coord[1] < size[1];
    }
}
