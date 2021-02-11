package Tasks;
public class Deadline extends Task {
    protected String by;
    protected String type = "D";

    public Deadline() {};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getType() {
        return type;
    }

    public String getPrintedLine() {
        return "[D]" + super.getPrintedLine() + " (by: " + by + ")";
    }
}