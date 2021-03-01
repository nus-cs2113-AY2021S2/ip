package TaskClasses;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public Task(Boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setAsDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toSaveFormat() {
        return " | " + isDone + " | " + description;
    }
}