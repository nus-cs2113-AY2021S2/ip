public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("", false);
    }

    public Task(String description, boolean isDone) {
        setDescription(description);
        setIsDone(isDone);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean isDone) {
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
