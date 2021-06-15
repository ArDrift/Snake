package snake;
import java.util.Comparator;

/**
* A rekordok pontszámainak összehasonlításáért felelős osztály.
*/
public class PtsComparator implements Comparator<HighScore> {

    /**
    * Pontszámok lekérése az egyes rekordokból, és azok összehasonlítása.
    * @param  a  bázis rekord, amihez hasonlítunk.
    * @param  b  másik rekord, amit hasonlítunk a bázishoz.
    * @return  int visszatérési érték a felülírt függvény szerint.
    */
    @Override
    public int compare(HighScore a, HighScore b) {
        return (Integer.valueOf(a.getPts())).compareTo(b.getPts());
    }
}
