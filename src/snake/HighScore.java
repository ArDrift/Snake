package snake;
import java.time.LocalDateTime;

/**
* A rekordokat tároló osztály.
*/
public class HighScore {

    /**
    * A rekordot állító neve.
    */
    private String name;

    /**
    * A rekord pontszáma.
    */
    private int pts;

    /**
    * A rekordhoz tartozó dátum
    */
    private LocalDateTime date;

    /**
    * Rekord létrehozása név és pontszám megadásával, a dátum a jelenlegi lesz.
    * @param  n  a játékos neve.
    * @param  p  a játékos pontszáma.
    */
    public HighScore(String n, int p) {
        name = n;
        pts = p;
        date = LocalDateTime.now();
    }

    /**
    * Rekord létrehozása a fentihez hasonló módon, extra dátum megadásával.
    * @param  n  a játékos neve.
    * @param  p  a játékos pontszáma.
    * @param  d  a rekord dátuma.
    */
    public HighScore(String n, int p, LocalDateTime d) {
        name = n;
        pts = p;
        date = d;
    }

    /**
    * Rekord visszaadása szövegként.
    * @return  a rekordhoz tartozó összes adat, olvasható formátumban.
    */
    @Override
    public String toString() {
        return new String(name + ": " + pts + " (" + date + ")");
    }

    /**
    * A rekordot állító nevének lekérése.
    * @return  rekordot állító neve.
    */
    public String getName() {
        return name;
    }

    /**
    * A rekord pontszámának lekérése.
    * @return  a rekordhoz tartozó pontszám.
    */
    public int getPts() {
        return pts;
    }

    /**
    * A rekord dátumának lekérése.
    * @return  a rekord dátuma.
    */
    public LocalDateTime getDate() {
        return date;
    }
}
