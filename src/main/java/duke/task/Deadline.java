package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        typeIcon = "D";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", by);
    }
}
