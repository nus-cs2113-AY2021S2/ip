package Duke.Task;

public class Task {
    protected String taskDetail;
    protected boolean isDone;
    
    private static String DONE = "done";
    private static String UNDO = "undo";

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? DONE : UNDO);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() { return isDone; }

    public String getTask() {
        return taskDetail;
    }

    public String getTaskType() {
        return null;
    }

    public String toString() {
        return "[" + getStatus() + "] " + getTask();
    }
}
