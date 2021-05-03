package snake;
import java.io.PrintWriter;

public class Field {
    private int width;
    private int height;
    private Cell[][] matrix;

    public Field(int w, int h) {
        width = w;
        height = h;
        setMatrix(w, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                getMatrix()[i][j] = new Cell('e');
            }
        }
    }

    public Field(Cell[][] m) {
        width = m[0].length;
        height = m.length;
        matrix = m;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int w, int h) {
        matrix = new Cell[h][w];
    }

    public void printMatrix(PrintWriter wt) {
        for (int c = 0; c < matrix.length; c++) {
            for (int r = 0; r < matrix[c].length; r++) {
                wt.print(matrix[c][r].toString());
                if (r != matrix.length-1) {
                    wt.print(" ");
                }
            }
            wt.println();
        }
    }

    public void setCell(int x, int y, char c) {
        getMatrix()[y][x].setType(c);
    }

    public Cell getCell(int x, int y) {
        return getMatrix()[y][x];
    }

    public String displayCell(Cell c) {
        switch (c.getType()) {
            case 'e': return "  ";
            case 'o': return "▒▒";
            case 's': return "██";
            case 'a': return "()";
            case 'U': return "/\\";
            case 'D': return "\\/";
            case 'R': return "█>";
            case 'L': return "<█";
            default: return "";
        }
    }

    public void print(int data) {
        String w = new String(new char[getSize()[0]*2]).replace("\0", "═");
        System.out.println("╔" + w + "╗" + "\r");
        for (int i = 0; i < getMatrix().length; i++) {
            System.out.print("║");
            for (int j = 0; j < getMatrix()[0].length; j++) {
                System.out.print(displayCell(getMatrix()[i][j]));
            }
            System.out.println("║" + "  ");
            int bw = String.valueOf(data).length();
            String bwStr = new String(new char[bw]).replace("\0", "═");
            switch (i) {
                case 0: System.out.print("PTS:" + "\r"); break;
                case 1: System.out.print("╔" + bwStr + "╗" + "\r"); break;
                case 2: System.out.print("║" + data  + "║" + "\r"); break;
                case 3: System.out.print("╚" + bwStr + "╝" + "\r"); break;
                default: System.out.print("\r");

            }
        }
        System.out.println("╚" + w + "╝" + "\r");
    }

    public int[] getSize() {
        return new int[] {width, height};
    }
}
