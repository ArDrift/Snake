package snake;

/**
* A pályaméret megváltoztatásáért felelős gomb, a Gomb ősosztály leszármazottja.
*/
public class ChangeFieldSizeBtn extends Button {
    /**
    * Konstruktor - ősosztály konstruktorával.
    * @param  n  a gomb neve.
    */
    public ChangeFieldSizeBtn(String n) {
        super(n);
    }

    /**
    * A gomb lenyomásakor használt függvény, belépés a pályaméret-állító menübe.
    */
    public void action() {
        SetFieldBtn.changeFieldSize();
    }
}
