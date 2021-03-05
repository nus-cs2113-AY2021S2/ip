package duke.task;

/**
 * Represents a task
 */
public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return status icon of a task
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Mark task as done
     */
    public void setAsDone() {
        this.isDone = true;
    }
}
