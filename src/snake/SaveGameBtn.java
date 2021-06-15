package snake;

/**
* A játék elmentéséért felelős gomb.
*/
public class SaveGameBtn extends Button {
    /**
    * A jelenlegi játék, Game típussal.
    */
    private Game game;

    /**
    * Az ősosztály konstrukora, kiegészítve az aktuális játék hozzárendelésével.
    * @param  n  a gomb neve
    */
    public SaveGameBtn(String n, Game g) {
        super(n);
        game = g;
    }

    /**
    * A játék elmentése.
    */
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
