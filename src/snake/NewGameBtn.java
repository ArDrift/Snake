package snake;

public class NewGameBtn extends Button {
    public NewGameBtn(String n) {
        super(n);
    }

    @Override
    public void action() {
        Game game = new Game(new Field(20, 20), new Snake(2, 2, 'U'), 0);
        try {
            game.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
