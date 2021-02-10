package duke.task;

public abstract class Task {
    protected String taskName;
    protected String taskType;
    protected boolean isCompleted;

    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isCompleted = false;
    }

    public String getTaskName() { return taskName; }

    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getTaskType() { return taskType; }

    public void setTaskType(String taskType) { this.taskType = taskType; }

    public boolean getIsCompleted() { return isCompleted; }

    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public abstract void printTask();
}
