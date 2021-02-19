public class Event extends Task{
    protected String by;
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + "(" + "at:" + by + ")";
    }
    public String getDescription() {
        return this.description;
    }
    public String getBy() {
        return this.by;
    }
    @Override
    public String stringToSave() {
        return "E ==> " + getStatusIcon() + " ==> " + getDescription() + " ==> " + getBy();
    }
}
