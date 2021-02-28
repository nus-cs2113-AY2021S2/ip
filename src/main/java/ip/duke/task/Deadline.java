package ip.duke.task;

/**
 * Represents a type of task with description and a deadline (date or time)
 * status is undone unless marked done by the user
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    public String toDataString() {
        return "D" + super.toDataString() + " | " + by;
    }
}
