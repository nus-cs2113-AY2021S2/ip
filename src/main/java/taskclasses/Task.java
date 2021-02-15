package taskclasses;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskType() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or space symbols
    }

    public String toString() {
        return ("[" + getStatusIcon() + "] " + getTaskName());
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTime() {
        return "";
    }

}
