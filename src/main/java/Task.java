import jdk.jshell.spi.ExecutionControl;

public class Task {
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

    public boolean getCompleted() { return isCompleted; }

    public void setCompleted() { isCompleted = true; }

    public void printTask() {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
