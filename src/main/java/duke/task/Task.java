package duke.task;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setAsDone() {
        this.isDone = true;
    }
}
