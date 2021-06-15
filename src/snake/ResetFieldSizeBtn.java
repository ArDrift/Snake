package snake;

/**
* A pályaméret alapértelmezettre állításáért felelős gomb.
*/
public class ResetFieldSizeBtn extends Button {
    /**
    * A gomb létrehozása az ősosztály konstruktorával
    * @param  n  a gomb neve.
    */
    public ResetFieldSizeBtn(String n) {
        super(n);
    }

    /**
    * A pályaméret alapértelmezettre állítása.
    */
    @Override
    public void action() {
        SetFieldBtn.resetFieldSize();
    }
}
