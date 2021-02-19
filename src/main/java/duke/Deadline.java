package duke;

/**
 * Represents a deadline that can be created and store in Duke.
 * An <code>Deadline</code> object corresponds to
 * a deadline represented by a deadline description, a deadline status, and a deadline time.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    /**
     * Returns the status, description, and time of a deadline in a line.
     *
     * @return Deadline status, description and time.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Saves the status, description and time of a deadline into file.
     *
     * @return Deadline status, description and time.
     */
    public String saveTask() {
        return "D" + super.saveTask() + "|" + by;
    }
}