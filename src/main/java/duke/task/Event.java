package duke.task;

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

    public String getTaskType() {
        return "event";
    }

    @Override
    public String getTaskDate(String dateFormat) {
        return at.formatDateWithDelimeter(dateFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTaskDate(Constants.DATE_FORMAT) + ")";
    }
}