public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");    // return X (done) or space (not done)
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + description;
    }
}
