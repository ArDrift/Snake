package snake;

public class ChangeFieldSizeBtn extends Button {
    public ChangeFieldSizeBtn(String n) {
        super(n);
    }

    public void action() {
        SetFieldBtn.changeFieldSize();
    }
}
