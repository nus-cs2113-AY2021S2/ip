public class Task {
    private boolean isCompleted;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() { return taskName; }

    public void setTaskName(String taskName) { this.taskName = taskName; }

    public boolean getCompleted() { return isCompleted; }

    public void setCompleted() { isCompleted = true; }

}
