package snake;

public class Game {
    private Field field;
    private Snake snake;
    private int pts;

    public Game(Field f, Snake s, int p) {
        field = f;
        snake = s;
        pts = p;
        field.getMatrix()[snake.getPos()[0]]
                         [snake.getPos()[1]].setType(snake.getDir());

    }

    public void start() {
        for (int[] b : snake.getBody()) {
            field.setCell(b[0], b[1], 's');
        }
        field.setCell(7, 9, 'o');
        field.setCell(1, 6, 'a');
        field.print();
    }
}
