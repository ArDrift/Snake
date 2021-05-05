package snake;

public class SaveGameBtn extends Button {
    private Game game;

    public SaveGameBtn(String n, Game g) {
        super(n);
        game = g;
    }

    @Override
    public void action() {
        try {
            game.saveGame();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
