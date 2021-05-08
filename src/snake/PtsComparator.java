package snake;
import java.util.Comparator;

public class PtsComparator implements Comparator<HighScore> {

    @Override
    public int compare(HighScore a, HighScore b) {
        return (new Integer(a.getPts())).compareTo(b.getPts());
    }
}
