package snake;

public class Field {
    private int width;
    private int height;
    private Cell[][] matrix;

    public Field(int w, int h) {
        width = w;
        height = h;
        setMatrix(w, h);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                getMatrix()[i][j] = new Cell('e');
            }
        }
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int w, int h) {
        matrix = new Cell[w][h];
    }

    public void setCell(int x, int y, char c) {
        getMatrix()[x][y].setType(c);
    }

    public String displayCell(Cell c) {
        switch (c.getType()) {
            case 'e': return "  ";
            case 'o': return "🧱";
            case 's': return "🐍";
            case 'a': return "🍎";
            case 'U': return "🔼";
            case 'D': return "🔽";
            case 'R': return "▶";
            case 'L': return "◀";
            default: return "";
        }
    }

    public void print() {
        for (int i = 0; i < getMatrix()[0].length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                System.out.print(displayCell(getMatrix()[j][i]));
            }
            System.out.println("\r");
        }
    }
}
