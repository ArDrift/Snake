package snake;

/**
* A Mentés beöltéséért felelős gomb osztálya.
*/
public class LoadSaveBtn extends Button {

    /**
    * Betöltés gomb létrehozása ősosztállyal.
    * @param  n  a gomb kiirandó neve.
    */
    public LoadSaveBtn(String n) {
        super(n);
    }

    /**
    * A gomb kiválasztásakor betöltjük a mentést, és elindítjuk a játékot.
    */
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
