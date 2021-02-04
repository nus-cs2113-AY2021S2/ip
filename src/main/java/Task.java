public class Task {
    protected String description;
    protected boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    //getter
    public String getStatusIcon(boolean isDone) {
        return (isDone ? "[\u2713] " : "[ ] "); //return tick or X symbols
    }

    //getter
    public String getDescription() {
        return description;
    }

    //setter, take note of the done
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon(isDone) + description;
    }
}