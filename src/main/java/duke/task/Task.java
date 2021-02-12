package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getTextIcon() { return (isDone ? "1" :" 0"); }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() { return isDone; }

    public String getTask() {
        return description;
    }

    public String getType() { return null; }

    public String getTime() { return null; }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
