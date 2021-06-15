package snake;
import java.io.PrintWriter;

/**
* A játékban használt pálya állapotának tárolásáért felelős osztály.
*/
public class Field {
    /**
    * A pálya szélesssége.
    */
    private int width;
    /**
    * A pálya magassága.
    */
    private int height;

    /**
    * A pályában lévő cellák mátrixa.
    */
    private Cell[][] matrix;

    /**
    * A pálya konstruktora, szélesség és magasság megadásával,
    * ilyenkor a mátrix feltöltődik csupa üres ('e') típusú Cellával.
    * @param  w  a pálya szélessége
    * @param  h  a pálya magassága
    */
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

    /**
    * A pálya konstruktora a mátrix megadásával, mentés betöltésekor használjuk.
    */
    public Field(Cell[][] m) {
        width = m[0].length;
        height = m.length;
        matrix = m;
    }

    /**
    * A pálya mátrixának lekérése.
    * @return  a pálya mátrixa.
    */
    public Cell[][] getMatrix() {
        return matrix;
    }

    /**
    * A pálya mátrixának inicializálása, egyelőre üresen, megfelelő hosszokkkal.
    * @param  w  a pályában használt mátrix szélessége
    * @param  h  a pályában használt mátrix magassága
    */
    public void setMatrix(int w, int h) {
        matrix = new Cell[h][w];
    }

    /**
    * A pálya mátrixának kiírása a Cellák típusával, illetve kígyó testének
    * esetén a Cella adatával, hogy mentésnél az mentődjön el. Az egymás
    * mellett lévő cellákat szóközök választják el egymástól, minden sor végén
    * soremelés van.
    * @param  wt  kiírás célja, ahova a mátrix ki lesz írva.
    */
    public void printMatrix(PrintWriter wt) {
        for (int c = 0; c < matrix.length; c++) {
            for (int r = 0; r < matrix[c].length; r++) {
                if (matrix[c][r].getType() == 's') {
                    wt.print(matrix[c][r].getData());
                } else {
                    wt.print(matrix[c][r].toString());
                }
                if (r != matrix.length-1) {
                    wt.print(" ");
                }
            }
            wt.println();
        }
    }

    /**
    * A mátrix egy adott koordinátájú Cellájának állítása, adott típusra.
    * @param  x  a Cella X koordinátája.
    * @param  y  a Cella Y koordinátája.
    * @param  c  a Cella kívánt típusa.
    */
    public void setCell(int x, int y, char c) {
        getMatrix()[y][x].setType(c);
    }

    /**
    * A mátrix egy adott koordinátájú Cellájának lekérése.
    * @param  x  a Cella X koordinátája.
    * @param  y  a Cella Y koordinátája.
    */
    public Cell getCell(int x, int y) {
        return getMatrix()[y][x];
    }

    /**
    * A megadott Cella visszaadása String-ként, vizuális reprezentációhoz.
    * @param  c  a Cella amit reprezentálni szeretnénk.
    * @return  a Cella, Unicode karakterekkel vizuálisan reprezentálva.
    */
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

    /**
    * A pálya kiírása a standard kimenetre, a mátrixot bekeretezve, a Cellák
    * vizuális reprezentációját használva, egy extra dobozzal,
    * amiben a megadott szöveget és számot jelezzük a felhasználónak.
    * @param  data  a kijelezni kívánt szám, játék közben pontszám,
    * vagy pályatervezésnél az akadályok száma.
    * @param  name  a doboz fölé kiírandó szöveg.
    */
    public void print(int data, String name) {
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
                case 0: System.out.print(name + "\r"); break;
                case 1: System.out.print("╔" + bwStr + "╗" + "\r"); break;
                case 2: System.out.print("║" + data  + "║" + "\r"); break;
                case 3: System.out.print("╚" + bwStr + "╝" + "\r"); break;
                default: System.out.print("\r");

            }
        }
        System.out.println("╚" + w + "╝" + "\r");
    }

    /**
    * A pálya méreteinek visszaadása.
    * @return  A pálya mérete szélesség, magasság sorrendben.
    */
    public int[] getSize() {
        return new int[] {width, height};
    }
}
