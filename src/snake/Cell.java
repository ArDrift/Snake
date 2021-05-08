package snake;

public class Cell {
    private char type;
    // 'e' = empty; 'o' = obstacle; 's' = snake; 'a' = apple
    // U/D/L/R = snake-head: ('U' = up; 'D' = down; 'R' = right; 'L' = left)
    private int data = -1;
    public Cell(char c) {
        type = c;
    }

    public Cell(char c, int d) {
        type = c;
        data = d;
    }

    public char getType() {
        return type;
    }

    protected void setType(char c) {
       type = c;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    public int getData() {
        return data;
    }

    public void setData(int d) {
        data = d;
    }
}
