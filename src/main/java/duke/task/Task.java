package duke.task;

public class Task {
    protected final String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "[]";
    }

    public String getStatusIcon() {
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
}
