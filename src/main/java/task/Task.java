package task;

public class Task {
    protected static final String MARK_DONE = "X";
    protected static final String MARK_UNDONE = " ";
    protected static final String TASK_SYMBOL_NA = " ";

    protected String completionStatus;
    protected String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.completionStatus = MARK_UNDONE;
    }

    public String getTaskSymbol() {
        return TASK_SYMBOL_NA;
    }

    public String getTaskCompletionStatus() {
        return this.completionStatus;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String getTaskTiming() {
        return "";
    }

    public String getFileFormat(){
        return getTaskSymbol() + " | " + getTaskCompletionStatus() + " | " + getTaskDescription();
    }

    public void setTaskAsDone() {
        this.completionStatus = MARK_DONE;
    }

    public boolean isDone() {
        if (this.completionStatus == MARK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}
