package Duke.Task;

public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() { return isDone; }

    public String getTask() {
        return taskDescription;
    }

    public String getTaskType() {
        return null;
    }

    public String getTaskTime() {
        return null;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
