package snake;
import java.util.ArrayList;

/**
* A játékban lévő kígyó kezeléséért felelős osztály.
*/
public class Snake {
    /**
    * A kígyó aktuális hossza.
    */
    private int length;
    /**
    * A kígyó fejének iránya, az irány angol nevének kezdőbetűjével jelölve.
    */
    private char dir;
    /**
    * A kígyó fejének pozíciója, X és Y koordinátával megadva.
    */
    private int[] pos = new int[2];
    /**
    * A kígyó teste, az általa kitöltött cellák X és Y pozícióival.
    */
    private ArrayList<int[]> body = new ArrayList<>();

    /**
    * Új kígyó létrehozása, alapértelmezett hosszal, a body attribútumot
    * a fej iránya és a hossz szerint feltöltve.
    * @param  px  a kígyó fejének X koordinátája.
    * @param  py  a kígyó fejének Y koordinátája.
    * @param  d  a kígyó fejének iránya, a dir attribútumhoz hasonlóan.
    */
    public Snake(int px, int py, char d) {
        pos[0] = px;
        pos[1] = py;
        dir = d;
        length = 2;
        switch (dir) {
            case 'U':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0], pos[1] + i});
                }
                break;
            case 'D':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0], pos[1] - i});
                }
                break;
            case 'R':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0] - i, pos[1]});
                }
                break;
            case 'L':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0] + i, pos[1]});
                }
                break;
            default: break;
        }
    }

    /**
    * A kígyó létrehozása a fentihez hasonlóan, de előre megadott test listával.
    */
    public Snake(int px, int py, char d, ArrayList<int[]> b) {
        pos[0] = px; pos[1] = py; dir = d;
        body = b; length = b.size();
    }

    /**
    * A kígyó pozíciójának lekérdezése.
    * @return  a kígyó pozíciója, X, Y sorrendben.
    */
    public int[] getPos() {
        return pos;
    }

    /**
    * A kígyó pozíciójának beállítása, X, Y sorrendben.
    */
    protected void setPos(int[] p) {
        pos[0] = p[0];
        pos[1] = p[1];
    }

    /**
    * A kígyó irányának lekérdezése.
    * @return  a kígyó iránya, a dir attribútum definiálásánál leírtaknak
    * megfelelően.
    */
    public char getDir() {
        return dir;
    }

    /**
    * A kígyó irányának beállítása.
    * @param  d  a kígyó iránya, az attribútum formátuma szerint.
    */
    protected void setDir(char d) {
        dir = d;
    }

    /**
    * A kígyó testének lekérdezése.
    * @return a kígyó teste.
    */
    public ArrayList<int[]> getBody() {
        return body;
    }

    /**
    * A kígyó hosszának lekérdezése.
    * @return  a kígyó hossza.
    */
    public int getLength() {
        return length;
    }

    /**
    * A kígyó hosszának beállítása.
    * @param  l  a kígyó új hossza.
    */
    public void setLength(int l) {
        length = l;
    }

    /**
    * A kígyó új pozíciójának lekérdezése,
    * ha az adott irányba mozogna 1 cellányit.
    * @param  dir  az irány amibe a kígyót mozgatni szeretnénk.
    * @return  az új pozíció amit a kígyó a mozgatás után így felvenne.
    */
    public int[] newPos(char dir) {
        switch (dir) {
            case 'U': return new int[] {getPos()[0], getPos()[1]-1};
            case 'D': return new int[] {getPos()[0], getPos()[1]+1};
            case 'R': return new int[] {getPos()[0]+1, getPos()[1]};
            case 'L': return new int[] {getPos()[0]-1, getPos()[1]};
            default: return new int[] {getPos()[0], getPos()[1]};
        }
    }

    /**
    * A kígyó mozgatása adott irányba, a test minden elemének arrébb tolásával.
    * @param  dir  a mozgatás iránya.
    */
    public void move(char dir) {
        int[] oldPos = {getPos()[0], getPos()[1]};
        setPos(newPos(dir));
        for (int i = 0; i < getBody().size(); i++) {
            int [] temp = {getBody().get(i)[0], getBody().get(i)[1]};
            getBody().get(i)[0] = oldPos[0];
            getBody().get(i)[1] = oldPos[1];
            oldPos[0] = temp[0];
            oldPos[1] = temp[1];
        }
    }

    /**
    * A kígyó farok pozíciójának lekérdezése, ami a test utolsó elemét jelenti.
    * @return  a kígyó farkának pozíciója.
    */
    public int[] getTailPos() {
        return getBody().get(getBody().size()-1);
    }

    /**
    * A kígyó meghosszabbítása, amit alma evésnél használunk.
    * A kígyó farkának pozícióját hozzáadja a testhez, és a hossz attribútumot
    * 1-gyel növeli. A játékon belül ez már arrébb tolás után történik.
    */
    public void grow() {
        body.add(new int[] {getTailPos()[0], getTailPos()[1]});
        setLength(getLength() + 1);
    }
}
