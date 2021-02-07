package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    private String getStatusIcon() {
        /* Return X if task is done, and space otherwise */
        return (isDone ? "X" : " ");
    }
}
