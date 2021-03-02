package tasks;

/**
 * Represents a task. A <code>Task</code> object contains its description
 * and 'isDone' boolean variable - indicating whether the task is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     * @param description Description of the task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method for description.
     * @return Description of the task object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for isDone.
     * @return Boolean variable isDone, indicating if the task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Getter method for status icon
     * @return Status Icon (tick or X symbols)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark a task done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
