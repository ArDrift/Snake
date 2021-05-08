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
            case 'U':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0], pos[1] + i});
                }
                break;
            case 'D':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0], pos[1] - i});
                }
                break;
            case 'R':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0] - i, pos[1]});
                }
                break;
            case 'L':
                for (int i = 1; i <= length; i++) {
                    body.add(new int[] {pos[0] + i, pos[1]});
                }
                break;
            default: break;
        }
    }

    public Snake(int px, int py, char d, ArrayList<int[]> b) {
        pos[0] = px; pos[1] = py; dir = d;
        body = b; length = b.size();
    }

    public int[] getPos() {
        return pos;
    }

    protected void setPos(int[] p) {
        pos[0] = p[0];
        pos[1] = p[1];
    }

    public char getDir() {
        return dir;
    }

    protected void setDir(char d) {
        dir = d;
    }

    public ArrayList<int[]> getBody() {
        return body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int l) {
        length = l;
    }

    public int[] newPos(char dir) {
        switch (dir) {
            case 'U': return new int[] {getPos()[0], getPos()[1]-1};
            case 'D': return new int[] {getPos()[0], getPos()[1]+1};
            case 'R': return new int[] {getPos()[0]+1, getPos()[1]};
            case 'L': return new int[] {getPos()[0]-1, getPos()[1]};
            default: return new int[] {getPos()[0], getPos()[1]};
        }
    }

    public void move(char dir) {
        int[] oldPos = {getPos()[0], getPos()[1]};
        setPos(newPos(dir));
        for (int i = 0; i < getBody().size(); i++) {
            int [] temp = {getBody().get(i)[0], getBody().get(i)[1]};
            getBody().get(i)[0] = oldPos[0];
            getBody().get(i)[1] = oldPos[1];
            oldPos[0] = temp[0];
            oldPos[1] = temp[1];
        }
    }

    public int[] getTailPos() {
        return getBody().get(getBody().size()-1);
    }

    public void grow() {
        body.add(new int[] {getTailPos()[0], getTailPos()[1]});
        setLength(getLength() + 1);
    }
}
