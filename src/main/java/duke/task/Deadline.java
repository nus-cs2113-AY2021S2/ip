package duke.task;

import duke.DateTime;
import duke.exception.InvalidInputException;

/**
 * A deadline-type task, with a 'by' dateTime indicating when it has to be done by
 */
public class Deadline extends Task {
    // This value indicates the version of this class when doing serialization
    public static final long serialVersionUID = 2L;

    protected DateTime byDateTime;

    /**
     * Initialize the deadline task and set the typeIcon to 'D'
     * @param description Description of the task, cannot be empty
     * @param byDateTime The date and time this task has to be done before
     * @throws InvalidInputException This is thrown if the task description is null or an empty string
     */
    public Deadline(String description, DateTime byDateTime) throws InvalidInputException {
        super(description);
        this.byDateTime = byDateTime;
        typeIcon = "D";
    }

    /**
     * Check whether this deadline ('by' date) falls on the given dateTime
     * @param dateTime The date for comparison
     * @return True when this task falls on the given `dateTime`
     */
    @Override
    public Boolean isSameDate(DateTime dateTime) {
        return byDateTime.isSameDate(dateTime);
    };

    /**
     * Converts this task to a descriptive string
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", byDateTime);
    }
}
