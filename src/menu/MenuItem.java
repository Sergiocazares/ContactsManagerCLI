package menu;

abstract public class MenuItem {
    private String description;

    public abstract void action();
    public abstract boolean isAvailable();

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}