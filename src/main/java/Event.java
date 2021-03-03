public class Event extends Task{
    protected String by;
    public Event(String description, String by) {
        super(description);
        this.by = by;
        this.SYMBOL = "E";
    }

    public String getDescription() {
        return this.description;
    }
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "\t[" + SYMBOL + "]" + super.toString() + "(" + "at:" + by + ")";
    }

    @Override
    public String stringToSave() {
        return SYMBOL + ARROW + getStatusIcon() + ARROW + getDescription() + ARROW + getBy();
    }
}
