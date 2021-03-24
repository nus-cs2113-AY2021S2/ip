package duke.task;

/**
 * Represent a task.
 */
public class Task {
    protected final String description;
    protected boolean isDone;
    protected String taskType;

    protected static int TRUE = 1;
    protected static int FALSE = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "[]";
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); //return space or tick symbols
    }

    /**
     * Mark the task as completed.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return a description of the task
     */
    public String getDescription() {
        return description;
    }

    public String toString() {
        return taskType + "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return the specific type of the task.
     *
     * @return the type of the task
     */
    public String getTaskType() {
        return taskType;
    }

    public String getDate() {
        return null;
    }

    /**
     * Check if a task has been done.
     *
     * @return true if the task has been done
     */
    public int getIsDone() {
        return isDone ? TRUE : FALSE ;
    }
}
