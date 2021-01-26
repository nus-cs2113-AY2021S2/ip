public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
