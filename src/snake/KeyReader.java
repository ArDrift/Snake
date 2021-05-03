package snake;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class KeyReader {
    private InputStream inputSrc;
    private BufferedReader in;

    public KeyReader(InputStream s) {
        inputSrc = s;
        in = new BufferedReader(new InputStreamReader(inputSrc));
    }

    public int[] getKeys(int length) throws IOException {
        char[] keys = new char[length];
        in.read(keys, 0, length);
        int[] keyCodes = new int[length];
        for (int i = 0; i < length; i++) {
            keyCodes[i] = (int)keys[i];
        }
        return keyCodes;
    }

    public boolean isArrow(int[] keys) {
        return keys[0] == 27 && keys[1] == 91 &&
               (keys[2] >= 65 && keys[2] <= 68);
    }

    public char arrowDir(int[] keyCodes) {
        switch (keyCodes[2]) {
            case 65: return 'U';
            case 66: return 'D';
            case 67: return 'R';
            case 68: return 'L';
            default: return 'X';
        }
    }

    public boolean ready() throws IOException {
        return in.ready();
    }
}
