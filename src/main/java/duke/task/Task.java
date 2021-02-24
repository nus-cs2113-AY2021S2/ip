package duke.task;

/**
 * Represents a task created by the user. A <code>Task</code> corresponds to
 * a task description and its completed status.
 */
public class Task {
    
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + getDescription();
    }
    
    public boolean isUrgent() {
        return !this.isDone();
    }
}