package snake;
import java.util.Comparator;

/**
* A kígyó testének elemenkénti összehasonlításáért felelős osztály.
* Ez mentés betöltésénél szükséges, hogy a kígyó testét megfelelő sorrendben
* állítsuk vissza.
*/
public class SnakeDataComparator implements Comparator<int[]> {

    /**
    * A paraméterként megadott tömbök 3. elemeinek összehasonlítása.
    * Egy mentésből beolvasott kígyó testhez tartozó cellában a 3. elem a hozzá
    * tartozó sorszám, az első 2 az X és Y koordináta.
    * @return  int visszatérési érték a felülírt függvény szerint.
    */
    @Override
    public int compare(int[] a, int[] b) {
        return (Integer.valueOf(a[2]).compareTo(b[2]));
    }
}
