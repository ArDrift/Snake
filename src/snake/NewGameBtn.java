package snake;

public class NewGameBtn extends Button {
    public NewGameBtn(String n) {
        super(n);
    }

    @Override
    public void action() {
        Game game = new Game(new Field(10, 10), new Snake(2, 2, 'A'), 0);
        game.start();
    }
}
