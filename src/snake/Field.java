package snake;
// import java.util.ArrayList;

public class Field {
    private int width;
    private int height;
    private Cell[][] matrix;
    //private ArrayList<ArrayList<Cell>> matrix = new ArrayList<>();

    public Field(int w, int h) {
        width = w;
        height = h;
        setMatrix(w, h);
        for (int i = 0; i < w; i++) {
            //getMatrix().add(new ArrayList<Cell>());
            for (int j = 0; j < h; j++) {
                //getMatrix().get(i).add(new Cell('e'));
                getMatrix()[i][j] = new Cell('e');
            }
        }
    }

/*    public ArrayList<ArrayList<Cell>> getMatrix() {
        return matrix;
    }*/
    public Cell[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int w, int h) {
        matrix = new Cell[w][h];
    }

    public void setCell(int x, int y, char c) {
        //getMatrix().get(x).get(y).setType(c);
        getMatrix()[x][y].setType(c);
    }

    public void print() {
        //for (int i = 0; i < getMatrix().get(0).size(); i++) {
        for (int i = 0; i < getMatrix()[0].length; i++) {
            //for (int j = 0; j < getMatrix().size(); j++) {
            for (int j = 0; j < getMatrix().length; j++) {
                System.out.print(j + "." + i + ": "
//                                + getMatrix().get(j).get(i).getType() + "  ");
                                  + getMatrix()[j][i].getType() + "  ");
            }
            System.out.println("");
        }
    }
}
