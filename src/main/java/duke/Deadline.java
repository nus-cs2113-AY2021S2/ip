package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.separator = "/by";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}