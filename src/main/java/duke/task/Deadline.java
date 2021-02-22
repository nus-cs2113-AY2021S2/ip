package duke.task;

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

    public String getTaskType() {
        return "deadline";
    }

    @Override
    public String getTaskDate(String dateFormat) {
        return by.formatDateWithDelimeter(dateFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTaskDate(Constants.DATE_FORMAT) + ")";
    }
}