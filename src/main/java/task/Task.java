package task;

public class Task {
    protected static final String MARK_DONE = "X";
    protected static final String MARK_UNDONE = " ";
    protected static final String TASK_SYMBOL_NA = " ";
    protected String taskStatus;
    protected String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = MARK_UNDONE;
    }

    public String getTaskSymbol() {
        return TASK_SYMBOL_NA;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String getTaskTiming() {
        return "";
    }

    public String getFileFormat(){
        return getTaskSymbol() + " | " + getTaskStatus() + " | " + getTaskDescription();
    }

    public void setTaskAsDone() {
        this.taskStatus = MARK_DONE;
    }

    public boolean isDone() {
        if (this.taskStatus == MARK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}
