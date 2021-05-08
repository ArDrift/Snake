package snake;
import java.util.Comparator;

public class SnakeDataComparator implements Comparator<int[]> {

    @Override
    public int compare(int[] a, int[] b) {
        return (new Integer(a[2]).compareTo(b[2]));
    }
}
