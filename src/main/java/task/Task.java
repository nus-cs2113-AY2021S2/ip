package task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    public static final String ICON_TASK_DONE = "Y";
    public static final String ICON_TASK_UNDONE = "N";
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
        return (isDone ? ICON_TASK_DONE : ICON_TASK_UNDONE);
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
