package task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    public static final String CHECK_MARK = "\u2713";
    public static final String X_MARK = "\u2718";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based on its done status.
     *
     * @return The status icon of the task based on its done status.
     */
    public String getStatusIcon() {
        return (isDone ? CHECK_MARK : X_MARK);
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the done status of the task.
     *
     * @return The done status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
