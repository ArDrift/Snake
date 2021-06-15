package snake;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

/**
* Az egyes leütött billentyűk beolvasásáért felelős osztály.
*/
public class KeyReader {

    /**
    * A bemenet forrása.
    */
    private InputStream inputSrc;

    /**
    * A bemenet olvasója.
    */
    private BufferedReader in;

    /**
    * Billentyű beolvasó létrehozása bemeneti forrás megadásával.
    * @param  s  a bemeneti forrás.
    */
    public KeyReader(InputStream s) {
        inputSrc = s;
        in = new BufferedReader(new InputStreamReader(inputSrc));
    }

    /**
    * Adott számú bájtnyi karakter beolvasása, ezeknek kódjait adjuk vissza.
    * @param  length  ahány bájtnyi karaktert be akarunk olvasni
    * @return  a beolvasott billentyű kódok.
    */
    public int[] getKeys(int length) throws IOException {
        char[] keys = new char[length];
        in.read(keys, 0, length);
        int[] keyCodes = new int[length];
        for (int i = 0; i < length; i++) {
            keyCodes[i] = (int)keys[i];
        }
        return keyCodes;
    }

    /**
    * Annak vizsgálata, hogy a beolvasott billentyű nyíl volt-e.
    * @param  keys  a billentyűkódok.
    * @return  igaz, ha nyíl volt, hamis, ha nem.
    */
    public boolean isArrow(int[] keys) {
        return keys[0] == 27 && keys[1] == 91 &&
               (keys[2] >= 65 && keys[2] <= 68);
    }

    /**
    * Annak vizsgálata, hogy a beolvasott billentyű enter volt-e.
    * @param  keys  a billentyűkódok.
    * @return  igaz, ha enter volt, hamis, ha nem.
    */
    public boolean isEnter(int[] keys) {
        return keys[0] == 13 && keys[1] == 0 && keys[2] == 0;
    }

    /**
    * Annak vizsgálata, hogy a beolvasott billentyű escape volt-e.
    * @param  keys  a billentyűkódok.
    * @return  igaz, ha escape volt, hamis, ha nem.
    */
    public boolean isEscape(int[] keys) {
        return keys[0] == 27 && keys[1] == 0 && keys[2] == 0;
    }

    /**
    * Annak vizsgálata, hogy a beolvasott billentyű szóköz volt-e.
    * @param  keys  a billentyűkódok.
    * @return  igaz, ha szóköz volt, hamis, ha nem.
    */
    public boolean isSpace(int[] keys) {
        return keys[0] == 32 && keys[1] == 0 && keys[2] == 0;
    }

    /**
    * A billentyűkódok irányhoz való rendelése, nyilak esetén használjuk.
    * @param  keyCodes  a beolvasott billentyűkódok.
    * @return  az irány, angol kezdőbetűjével megadva.
    */
    public char arrowDir(int[] keyCodes) {
        switch (keyCodes[2]) {
            case 65: return 'U';
            case 66: return 'D';
            case 67: return 'R';
            case 68: return 'L';
            default: return 'X';
        }
    }

    /**
    * @return igaz, ha rendelkezésre áll beolvasható bemenet, hamis, ha nem.
    */
    public boolean ready() throws IOException {
        return in.ready();
    }
}
