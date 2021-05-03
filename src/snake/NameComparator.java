package snake;
import java.util.Comparator;

public class NameComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore a, HighScore b) {
        return a.getName().compareTo(b.getName());
    }
}
