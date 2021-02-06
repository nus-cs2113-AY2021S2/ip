package Tasks;
public class Deadline extends Task {
    protected String by;

    public Deadline() {};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getPrintedLine() {
        return "[D]" + super.getPrintedLine() + " (by: " + by + ")";
    }
}