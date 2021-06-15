package snake;
import java.util.Comparator;

/**
* A rekordok neveinek összehasonlításáért felelős osztály.
*/
public class NameComparator implements Comparator<HighScore> {
    /**
    * Nevek lekérése az egyes rekordokból, és azok összehasonlítása.
    * @param  a  bázis rekord, amihez hasonlítunk.
    * @param  b  másik rekord, amit hasonlítunk a bázishoz.
    * @return  int visszatérési érték a felülírt függvény szerint.
    */
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getName().compareTo(b.getName());
    }
}
