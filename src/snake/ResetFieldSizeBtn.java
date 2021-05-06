package snake;

public class ResetFieldSizeBtn extends Button {
    public ResetFieldSizeBtn(String n) {
        super(n);
    }

    public void action() {
        SetFieldBtn.resetFieldSize();
    }
}
