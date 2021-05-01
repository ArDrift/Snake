package snake;

public class Cell {
    private char type;
    // 'e' = empty; 'o' = obstacle; 's' = snake; 'a' = apple
    // U/D/L/R = snake-head: ('U' = up; 'D' = down; 'R' = right; 'L' = left)
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
