package snake;

public class Game {
    private Field field;
    private Snake snake;
    private int pts;

    public Game(Field field, Snake snake, int pts) {
        this.field = field;
        this.snake = snake;
        this.pts = pts;
    }

    public void start() {
        field.setCell(2, 4, 's');
        field.setCell(7, 9, 'o');
        field.setCell(1, 6, 'a');
        field.print();
    }
}
