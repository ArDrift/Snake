package snake;
import java.util.ArrayList;

public class Snake {
    private int length;
    private char dir;
    private int[] pos = new int[2];
    private ArrayList<int[]> body = new ArrayList<>();

    public Snake(int px, int py, char d) {
        pos[0] = px;
        pos[1] = py;
        dir = d;
        length = 2;
        switch (dir) {
            case 'U': body.add(new int[] {pos[0], pos[1] + 1});
                      body.add(new int[] {pos[0], pos[1] + 2});
                break;
            case 'D': body.add(new int[] {pos[0], pos[1] - 1});
                      body.add(new int[] {pos[0], pos[1] - 2});
                break;
            case 'R': body.add(new int[] {pos[0] - 1, pos[1]});
                      body.add(new int[] {pos[0] - 2, pos[1]});
                break;
            case 'L': body.add(new int[] {pos[0] + 1, pos[1]});
                      body.add(new int[] {pos[0] + 2, pos[1]});
                break;
            default: break;
        }
    }

    public int[] getPos() {
        return pos;
    }

    public char getDir() {
        return dir;
    }

    public ArrayList<int[]> getBody() {
        return body;
    }
}
