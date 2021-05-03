package snake;
import java.time.LocalDateTime;

public class HighScore {
    private String name;
    private int pts;
    private LocalDateTime date;

    public HighScore(String n, int p) {
        name = n;
        pts = p;
        date = LocalDateTime.now();
    }

    public HighScore(String n, int p, LocalDateTime d) {
        name = n;
        pts = p;
        date = d;
    }

    @Override
    public String toString() {
        return new String(name + ": " + pts + " (" + date + ")");
    }

    public String getName() {
        return name;
    }

    public int getPts() {
        return pts;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
