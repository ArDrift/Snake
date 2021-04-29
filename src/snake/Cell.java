package snake;

public class Cell {
    private char type;
    // 'e' = empty; 'o' = obstacle; 's' = snake; 'a' = apple
    // A/B/C/D = snake-head: ('A' = up; 'B' = down; 'C' = right; 'D' = left)
    public Cell (char c) {
        type = c;
    }

    public char getType() {
        return type;
    }

    protected void setType(char c) {
       type = c;
    }
}
