package duke.task;

/**
 * Task that has o be done by a certain date/time.
 */
public class Deadline extends Task {
    private final String date;

    /**
     * Constructor
     * @param taskName Name of task.
     * @param date Date/time where the task must be done by.
     */
    public Deadline(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date);
    }

    @Override
    public String getData() {
        return String.format("D;%d;%s;%s", (isDone ? 1 : 0), taskName, date);
    }
}
