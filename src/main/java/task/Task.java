package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X if done else empty space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return partial display message when task added
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveToFile() {
        return null;
    }
}
