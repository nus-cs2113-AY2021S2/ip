package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols, tick is \u2713
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String saveToHardDisk() {
        int isDone = this.isDone ? 1 : 0;
        return " | " + isDone + " | " + description;
    }
}
