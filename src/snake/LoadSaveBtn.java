package snake;

public class LoadSaveBtn extends Button {
    public LoadSaveBtn(String n) {
        super(n);
    }

    @Override
    public void action() {
        try {
            Game save = new Game();
            save = save.loadGame();
            save.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
