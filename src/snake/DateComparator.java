package snake;
import java.util.Comparator;

public class DateComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getDate().compareTo(b.getDate());
    }
}
