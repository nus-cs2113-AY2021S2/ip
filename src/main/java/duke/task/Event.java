package duke.task;

/**
 * Task that happens at a specific date/time.
 */
public class Event extends Task {
    private final String date;

    /**
     * Constructor
     * @param taskName Name of event.
     * @param date Date/time of the event.
     */
    public Event(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }

    @Override
    public String getData() {
        return String.format("E;%d;%s;%s", (isDone?1:0), taskName, date);
    }
}
