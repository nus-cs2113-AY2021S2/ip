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

    public void setAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return taskType + "[" + getStatusIcon() + "] " + description;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getDate() {
        return null;
    }

    public int getIsDone() {
        return isDone ? TRUE : FALSE ;
    }
}
