package snake;

abstract public class Button {
    protected String name;
    protected boolean active;

    public Button(String n) {
        name = n;
    }

    abstract public void action();

    @Override
    public String toString() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    protected void setActive(boolean a) {
        active = a;
    }
}
