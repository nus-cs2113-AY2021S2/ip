package duke.task;

/**
 * Children object of Task class
 * with an additional date of the task
 */
public class Event extends Task {

    protected String on;

    /**
     * Create Event object
     *
     * @param description
     * @param on
     */
    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    /**
     * Return task with status icon
     *
     * @return task with status icon
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + on + ")";
    }
}
