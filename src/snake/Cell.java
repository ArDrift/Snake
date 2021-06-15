package snake;

/**
* A pálya mátrixában használt cellák osztálya.
*/
public class Cell {
    /**
    * A cella típusa, karakterrel jelölve:
    * 'e' = empty (üres), 'o' = obstacle (akadály),
    * 's' = snake (kígyó), 'a' = apple (alma). Kígyó feje:
    * 'U' = up (fel); 'D' = down (le); 'R' = right (jobb); 'L' = left (bal)
    */
    private char type;
    /**
    * A cella adata, típus mellett, ez a kígyó testrészeinek sorszámát tárolja,
    * hogy mentésnél helyes sorrendben lehessen azt visszatölteni.
    */
    private int data = -1;
    /**
    * Cella konstruktora, típus megadásával.
    * @param  c  a cella típusa, a fentiek szerint.
    */
    public Cell(char c) {
        type = c;
    }

    /**
    * Cella másik konstruktora, típus és adat megadásával.
    * @param  c  a cella típusa, a fentiek szerint.
    * @param  d  a cellához tartozó adat.
    */
    public Cell(char c, int d) {
        type = c;
        data = d;
    }

    /**
    * A cella típusának lekérdezése.
    * @return  a cella típusa.
    */
    public char getType() {
        return type;
    }

    /**
    * A cella típusának beállítása.
    * @param  c  a cella kívánt típusa.
    */
    protected void setType(char c) {
       type = c;
    }

    /**
    * A cella String-gé konvertálása.
    * @return  a cella szöveges reprezentációja.
    */
    @Override
    public String toString() {
        return String.valueOf(type);
    }

    /**
    * A cella adatának lekérdezése.
    * @return  a cellához tartozó adat.
    */
    public int getData() {
        return data;
    }

    /**
    * A cella adatának beállítása.
    * @param  d  a cellához tartozó adat.
    */
    public void setData(int d) {
        data = d;
    }
}
