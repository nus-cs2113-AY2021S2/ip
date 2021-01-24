public class Task {
    private boolean completed;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getTaskName() { return taskName; }

    public void setTaskName(String taskName) { this.taskName = taskName; }

    public boolean getCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

}
