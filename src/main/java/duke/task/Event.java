package duke.task;

/**
 * Represents the event task. An Event object corresponds to the task with a set date input by the user. 
 */
import duke.Constants;
import duke.Date;

public class Event extends Task {
    protected Date at;

    /**
     * Creates an Event object. 
     * 
     * @param description Name of event. 
     * @param at Date of event occurrence. 
     */
    public Event(String description, String at) {
        super(description);
        this.at = new Date(at);
    }

    /**
     * Retrieves the type of task. In this case, it is event. 
     */
    public String getTaskType() {
        return "event";
    }

    /**
     * Returns the formatted event task for display. 
     */
    @Override
    public String getTaskDate(String dateFormat) {
        return at.formatDateWithDelimeter(dateFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTaskDate(Constants.DATE_FORMAT) + ")";
    }
}