package snake;
import java.io.IOException;

/**
* Saját, testreszabott pálya osztálya, a pályatervezésért felelős.
*/
public class CustomField {
    /**
    * A testreszabott pálya, Field típussal.
    */
    private Field field;

    /**
    * Az egyedi pálya konstruktora, adott mérettel.
    * @param  size  a pálya mérete.
    */
    public CustomField(int size) {
        field = new Field(size, size);
    }

    /**
    * Egyedi pálya tervezéséért felelős függvény, amely kirajzolja
    * az eddigi pályát, és a felhasználó irányítása szerint változtatja azt,
    * a kimenetet mindig frissítve.
    * @return  a testreszabott pálya.
    */
    public Field makeField() throws IOException, InterruptedException {
        int cPosX = 0; int cPosY = 0; int obstacles = 0;
        field.setCell(cPosX, cPosY, 's');

        Main.cursorToZero();
        field.print(obstacles, "Obstacles:");
        System.out.println("");
        System.out.println("      Control: ↑ ↓ → ← SPACE ENTER");
        KeyReader keyR = new KeyReader(System.in);
        int[] keys = keyR.getKeys(3);
        while (!keyR.isEnter(keys)) {
            if (keyR.isSpace(keys)) {
                if (field.getCell(cPosX, cPosY).getType() != 'o') {
                    field.setCell(cPosX, cPosY, 'o');
                    obstacles += 1;
                } else {
                    field.setCell(cPosX, cPosY, 'e');
                    obstacles -= 1;
                }
            } else if (keyR.isArrow(keys)) {
                if (field.getCell(cPosX, cPosY).getType() != 'o') {
                    field.setCell(cPosX, cPosY, 'e');
                }
                switch (keyR.arrowDir(keys)) {
                    case 'U':
                        if (cPosY == 0) {
                            cPosY = field.getSize()[1]-1;
                        } else {
                            cPosY -= 1;
                        }
                        break;
                    case 'D':
                        if (cPosY == field.getSize()[1]-1) {
                            cPosY = 0;
                        } else {
                            cPosY += 1;
                        }
                        break;
                    case 'R':
                        if (cPosX == field.getSize()[0]-1) {
                            cPosX = 0;
                        } else {
                            cPosX += 1;
                        }
                        break;
                    case 'L':
                        if (cPosX == 0) {
                            cPosX = field.getSize()[0]-1;
                        } else {
                            cPosX -= 1;
                        }
                        break;
                }
                if (field.getCell(cPosX, cPosY).getType() != 'o') {
                    field.setCell(cPosX, cPosY, 's');
                }
            }
            Main.cursorToZero();
            field.print(obstacles, "Obstacles:");
            keys = keyR.getKeys(3);
        }
        if (field.getCell(cPosX, cPosY).getType() != 'o') {
            field.setCell(cPosX, cPosY, 'e');
        }
        Main.clearScr();
        return field;
    }

    /**
    * Az egyedi pálya lekérése.
    * @return  az egyedi pálya.
    */
    public Field getField() {
        return field;
    }
}
