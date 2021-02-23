package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatus() + description;
    }

    public String getTaskType() {
        return "task";
    }
}
