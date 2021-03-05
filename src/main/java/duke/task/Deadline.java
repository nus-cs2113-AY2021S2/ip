package duke.task;

/**
 * Represents a type of a task with description and deadline.
 */
public class Deadline extends Task {
    String endTime;

    /**
     * Creates a Deadline task with description and the end time of deadline.
     *
     * @param description description of a task
     * @param endTime     end time of a deadline task
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Get the end time of a deadline task.
     *
     * @return String of end time
     */
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * {@inheritDoc}
     *
     * @return concatenation of task status icon, its description and its end time.
     */
    @Override
    public String toString() {
        return getStatusIcon() + super.getDescription() + "(by: " + endTime + ")";
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }
}
