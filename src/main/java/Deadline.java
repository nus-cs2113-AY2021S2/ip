public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.SYMBOL = "D";
    }

    public String getDescription() {
        return this.description;
    }
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "\t[" + SYMBOL+ "]" + super.toString() + "(" + "by:" + by + ")";
    }

    @Override
    public String stringToSave() {
        return SYMBOL  + getStatusIcon() + ARROW + getDescription() + ARROW + getBy();
    }
}
