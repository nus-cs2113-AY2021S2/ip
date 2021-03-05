package duke.task;

/**
 * Children object of Task class
 * with an additional due date of the task
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Create Deadline object
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return task with statue icon
     *
     * @return task with status icon
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
