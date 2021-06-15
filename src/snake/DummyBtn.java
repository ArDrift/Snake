package snake;

/**
* Üres gomb, aminek megnyomását vagy máshol vizsgáljuk, vagy olyan funkciót
* szolgál, ami nem követeli meg külön függvény lefutását megnyomáskor.
*/
public class DummyBtn extends Button {
    /**
    * Gomb létrehozása névvel, ősosztályból.
    * @param  n  a kiírandó név
    */
    public DummyBtn(String n) {
        super(n);
    }

    /**
    * Megnyomáskor a vezérlés visszatér oda, ahol meghívták.
    */
    @Override
    public void action() {
        return;
    }
}
