package duke.task;

/**
 * Represents a type of a task with description and event time.
 */
public class Event extends Task {
    String eventTime;

    /**
     * Creates a Event task with description and the event time.
     *
     * @param description description of a task
     * @param eventTime   event time of a event task
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Get event time of a Event task.
     *
     * @return String of event time
     */
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * {@inheritDoc}
     *
     * @return concatenation of task status icon, its description and its event time.
     */
    @Override
    public String toString() {
        return getStatusIcon() + super.getDescription() + "(at: " + eventTime + ")";
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }
}
