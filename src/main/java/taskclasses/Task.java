package taskclasses;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or space symbols
    }

    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.taskName);
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
