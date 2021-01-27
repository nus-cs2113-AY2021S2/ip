public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "\u2713" : "\u2718");  // return tick or X symbol
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }
}
