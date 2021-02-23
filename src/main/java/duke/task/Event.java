package duke.task;

import duke.DateTime;
import duke.exception.InvalidInputException;

/**
 * An event-type class, which has a dateTime instance marking its happening time
 */
public class Event extends Task {
    // This value indicates the version of this class when doing serialization
    public static final long serialVersionUID = 2L;

    protected DateTime at;

    /**
     * Initialize the event task and set the typeIcon to 'E'
     * @param description Description of the task, cannot be empty
     * @param at The date and time this event falls at
     * @throws InvalidInputException This is thrown if the task description is null or an empty string
     */
    public Event(String description, DateTime at) throws InvalidInputException {
        super(description);
        this.at = at;
        typeIcon = "E";
    }

    /**
     * Check whether this event occuring date ('at' date) falls on the given dateTime
     * @param dateTime The date for comparison
     * @return True when this task falls on the given `dateTime`
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return at.isSameDate(dateTime);
    };

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", at);
    }
}
