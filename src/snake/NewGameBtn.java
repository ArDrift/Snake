package snake;
import java.util.Random;

/**
* Új játék indításáért felelős gomb.
*/
public class NewGameBtn extends Button {
    /**
    * Gomb létrehozása az ősosztály konstruktora szerint.
    * @param  n  a gomb neve.
    */
    public NewGameBtn(String n) {
        super(n);
    }

    /**
    * A gombot lenyomva új játék indul, a beállított (vagy alapértelmezett)
    * pályamérettel és pályával, a kígyó irányát véletlenszerűen megválasztva.
    * Ha a felhasználó saját pálya létrehozása után új pályaméretet állít be,
    * az felülírja a korábban létrehozott egyedi pályát.
    */
    @Override
    public void action() {
        int fSize = SetFieldBtn.getFieldSize();
        int d = new Random().nextInt(4);
        char dir = ' ';
        switch (d) {
            case 0: dir = 'U'; break;
            case 1: dir = 'D'; break;
            case 2: dir = 'R'; break;
            case 3: dir = 'L'; break;
        }
        Field field = new Field(fSize, fSize);
        Snake snake = new Snake(fSize/2, fSize/2, dir);

        if (CustomFieldBtn.isCustom()) {
            field = CustomFieldBtn.getCustomField();
        }

        Game game = new Game(field, snake, 0);
        try {
            game.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
