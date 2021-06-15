package snake;

/**
* A pályatervező gombja, belépést biztosít a pályatervezőbe.
*/
public class CustomFieldBtn extends Button {
    /**
    * A testreszabott pálya.
    * Statikus, hogy más osztályok is elérjék, pl. új játék indításánál.
    */
    private static Field customField;

    /**
    * Megmondja, hogy egyedi-e a pálya, vagy az alapértelmezett.
    * Ez is új játék indításánál szükséges, ezért szintén statikus.
    */
    private static boolean isCustom = false;

    /**
    * A gomb konstruktora, ősosztály használatával.
    * @param  n  a gomb neve.
    */
    public CustomFieldBtn(String n) {
        super(n);
    }

    /**
    * A gomb lenyomásakor lefutó metódus, létrehoz egy pályát,
    * a korábban beállított pályamérettel, majd belép a pályatervezőbe, és
    * annak visszatérési értékét állítja be új pályának.
    */
    @Override
    public void action() {
        CustomField field = new CustomField(SetFieldBtn.getFieldSize());
        try {
            customField = field.makeField();
            setCustom(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
    * Egyedi-e a pálya, vagy alapértelmezett.
    * @return  igaz, ha egyedi, hamis, ha alapértelmezett
    */
    public static boolean isCustom() {
        return isCustom;
    }

    /**
    * Egyediség állítása.
    * @param  c  a pálya egyedi-e, vagy alapértelmezett.
    */
    public static void setCustom(boolean c) {
        isCustom = c;
    }

    /**
    * A testreszabott pálya lekérése.
    * @return  az éppen aktuális egyedi pálya.
    */
    public static Field getCustomField() {
        return customField;
    }
}
