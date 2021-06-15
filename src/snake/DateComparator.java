package snake;
import java.util.Comparator;

/**
* A rekordok dátumainak összehasonlításáért felelős osztály.
*/
public class DateComparator implements Comparator<HighScore> {
    /**
    * Dátumok lekérése az egyes rekordokból, és azok összehasonlítása.
    * @param  a  bázis rekord, amihez hasonlítunk.
    * @param  b  másik rekord, amit hasonlítunk a bázishoz.
    * @return  int visszatérési érték a felülírt függvény szerint.
    */
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getDate().compareTo(b.getDate());
    }
}
