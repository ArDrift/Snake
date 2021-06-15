package snake;

/**
* A menükben használt absztrakt gomb ősosztály.
*/
abstract public class Button {
    /**
    * A gomb neve.
    */
    private String name;
    /**
    * Azt jelzi, hogy a gomb éppen aktív-e.
    */
    private boolean active;

    /**
    * @param  n  a gomb neve, amit kiíráskor használ a program.
    */
    public Button(String n) {
        name = n;
    }

    /**
    * A gomb kiválasztásakor futtatandó metódus.
    */
    abstract public void action();

    /**
    * A gomb szöveggé konvertálása.
    * @return  a gomb neve.
    */
    @Override
    public String toString() {
        return name;
    }

    /**
    * Annak lekérdezése, hogy a gomb éppen aktív-e.
    * @return  aktív, vagy nem.
    */
    public boolean isActive() {
        return active;
    }

    /**
    * A gomb aktív állapotának beállítása.
    * @param  a  beállítani kívánt aktív állapot.
    */
    protected void setActive(boolean a) {
        active = a;
    }
}
