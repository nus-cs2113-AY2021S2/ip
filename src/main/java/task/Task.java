package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (getIsDone() ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
