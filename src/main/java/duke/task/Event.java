package duke.task;

/**
 * Represents the event task. An Event object corresponds to the task with a set date input by the user. 
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an Event object. 
     * 
     * @param description Name of event. 
     * @param at Date of event occurrence. 
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieves the type of task. In this case, it is event. 
     */
    public String getTaskType() {
        return "event";
    }

    /**
     * Returns the date specified in the event object. 
     */
    @Override
    public String getTaskDate() {
        return at;
    }

    /**
     * Returns the formatted event task for display. 
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}