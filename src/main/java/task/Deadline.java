package task;

/**
 * Represents a DeadLine that contains a description, and a date or time to complete it by.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date or time to complete the DeadLine by.
     *
     * @return The date or time to complete the DeadLine by.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
