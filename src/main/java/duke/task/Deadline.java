
package duke.task;

/**
 * Task type is deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * @param description is the details of what the task is.
     * @param by is the time by which the the task should be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return the deadline description and status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + description + " (by:" + by + ")";
    }
}