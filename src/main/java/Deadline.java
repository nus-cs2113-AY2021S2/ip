public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "\t[D] " + super.toString() + "(" + "by:" + by + ")";
    }
    public String getDescription() {
        return this.description;
    }
    public String getBy() {
        return this.by;
    }
    @Override
    public String stringToSave() {
        return "D ==> " + getStatusIcon() + " ==> " + getDescription() + " ==> " + getBy();
    }
}
