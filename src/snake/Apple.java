package snake;

/**
* A játék pályáján található alma osztálya.
*/
public class Apple {
    /**
    * Az alma pozíciója, X, Y sorrendben.
    */
    private int[] pos = new int[2];

    /**
    * Alma létrehozása, pozíció megadásával.
    * @param  p  a pozíció, X, Y sorrendben.
    */
    public Apple(int[] p) {
        pos[0] = p[0]; pos[1] = p[1];
    }

    /**
    * A pozíció lekérdezése.
    * @return  a pozíció, X és Y.
    */
    public int[] getPos() {
        return pos;
    }

    /**
    * Pozíció beállítása.
    * @param  p  a kívánt pozíció, X és Y..
    */
    protected void setPos(int[] p) {
        pos[0] = p[0]; pos[1] = p[1];
    }
}
