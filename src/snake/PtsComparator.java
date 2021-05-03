package snake;
import java.util.Comparator;

public class PtsComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getPts() > b.getPts() ? 1 : a.getPts() < b.getPts() ? -1 : 0;
    }
}
