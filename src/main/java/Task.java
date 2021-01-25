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

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or empty
    }
}