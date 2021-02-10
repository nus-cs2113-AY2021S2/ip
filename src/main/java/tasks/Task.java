package tasks;

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

    public String getStatus() {
        return "[" + getStatusIcon() + "]";
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return getStatus() + " " + getDescription();
    }

    public void taskComplete() {
        isDone = true;
    }


}

