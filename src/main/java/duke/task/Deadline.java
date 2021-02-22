package duke.task;

/**
 * Represents the deadline task. An Deadline object corresponds to the task with a due date input by the user. 
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline object. 
     * 
     * @param description Name of task. 
     * @param by Deadline of task to complete. 
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the task type. In this case, it is deadline. 
     */
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Returns the date specified in the dealdine object. 
     */
    @Override
    public String getTaskDate() {
        return by;
    }

    /**
     * Returns the formatted deadline task for display. 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}