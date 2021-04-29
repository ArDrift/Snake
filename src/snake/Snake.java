package snake;

public class Snake {
    private int length;
    private Char dir;
    private int[] pos = new int[2];

    public Snake(int px, int py, int d) {
        pos[0] = px;
        pos[1] = py;
        dir = d;
        length = 2;
    }
}
