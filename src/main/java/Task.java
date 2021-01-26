public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setAsDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }
}
