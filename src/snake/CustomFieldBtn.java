package snake;

public class CustomFieldBtn extends Button {
    private static Field customField;
    private static boolean isCustom = false;
    public CustomFieldBtn(String n) {
        super(n);
    }

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

    public static boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean c) {
        isCustom = c;
    }

    public static Field getCustomField() {
        return customField;
    }
}
