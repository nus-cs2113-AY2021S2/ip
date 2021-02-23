package duke.task;

/**
 * Deals with deadline task which consists of deadline.
 */
public class Deadline extends Task {
    /**
     * Deadline of the task.
     */
    private String by;

    /**
     * Constructs new deadline task.
     *
     * @param description Description of the new deadline task.
     * @param by Deadline for the new deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getBy() + ")";
    }
}
