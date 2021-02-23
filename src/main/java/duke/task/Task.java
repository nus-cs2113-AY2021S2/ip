package duke.task;

import java.io.Serializable;

/**
 * Parent class for more specific task child class.
 * Contains basic information applicable to all task-related classes.
 */
public class Task implements Serializable {
    /**
     * Variable use for storing task description.
     */
    protected String description;
    /**
     * Status to see whether a task is done.
     */
    protected boolean isDone;

    /**
     * Constructs new task with no description.
     */
    public Task() {
        this("");
    }

    /**
     * Constructs new task with description provided.
     *
     * @param description Description for the new task.
     */
    public Task(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mark the status of current task based on isDone value.
     *
     * @param isDone Boolean value indicating whether a task is done or not.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Retrieves the status icon based on status of current task.
     *
     * @return Status icon in unicode format.
     */
    public String getStatusIcon() {
        return (getIsDone() ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
