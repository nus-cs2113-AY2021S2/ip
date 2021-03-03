package duke.task;

/**
 * Representation of generic task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task instance with description.
     *
     * @param description description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status icon of a task
     *
     * @return status icon
     */
    protected String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Get description of a task
     *
     * @return description of a task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set status of a task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Get status of a task.
     *
     * @return true if task is done and false if not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Get string representation of a task.
     *
     * @return concatenation of task status icon and its description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}
