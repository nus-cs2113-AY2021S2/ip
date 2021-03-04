package duke.task;

/**
 * Each instance represents a task created by the user.
 * A Task has two fields, a description and a boolean isDone to indicate if it's completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for the Task Class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a cross if task is done or single space if not done.
     *
     * @return String cross or single space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a String that contains all details of the task (status and description).
     *
     * @return String containing all details of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done/completed.
     */
    public void markAsDone() {
        isDone = true;
    }
}
