package duke.task;

/**
 * Represents the deadline task. An Deadline object corresponds to the task with a due date input by the user. 
 */
import duke.Constants;
import duke.Date;

public class Deadline extends Task {
    protected Date by;

    /**
     * Creates a Deadline object. 
     * 
     * @param description Name of task. 
     * @param by Deadline of task to complete. 
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new Date(by);
    }

    /**
     * Retrieves the task type. In this case, it is deadline. 
     */
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Formats the date according to the dateFormat provided.
     * 
     * @return Formatted date. 
     */
    @Override
    public String getTaskDate(String dateFormat) {
        return by.formatDateWithDelimeter(dateFormat);
    }

    /**
     * Returns the formatted deadline task for display. 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTaskDate(Constants.DATE_FORMAT) + ")";
    }
}