package duke.task;

/**
 * task type is event.
 */
public class Event extends Task {
    protected String at;

    /**
     * @param description is the description of the event.
     * @param at is the time which the event will be held.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * @return the event description and status.
     */
    @Override
    public String toString() {
        return "[E]"+ super.toString() + " (at:" +at + ")";
    }
}
