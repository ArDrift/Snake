package snake;
import java.util.Comparator;
import java.time.LocalDateTime;

public class DateComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getDate().compareTo(b.getDate());
    }
}
