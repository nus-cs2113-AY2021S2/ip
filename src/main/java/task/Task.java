package task;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String Description) {
        this.description = Description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718] "); //return tick or X symbols
    }

    public String toString() {
        return getStatusIcon()  + description;
    }

    public boolean setDone() {
        return isDone = true;
    }
}