public class Task{
    protected String description;
    protected boolean isDone;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void addDescription(String description) {
        this.description = description;
    }

    public void setAsDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u0020");
    }

    public String printDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}