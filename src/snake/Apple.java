package snake;

public class Apple {
    private int[] pos = new int[2];
    private boolean eaten = false;

    public Apple(int[] p) {
        pos[0] = p[0]; pos[1] = p[1];
    }

    public int[] getPos() {
        return pos;
    }

    protected void setPos(int[] p) {
        pos[0] = p[0]; pos[1] = p[1];
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean a) {
        eaten = a;
    }
}
