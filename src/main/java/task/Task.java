package task;

/**
 * Task class for storing the information of each task.
 */
public class Task {
    protected static final String MARK_DONE = "X";
    protected static final String MARK_UNDONE = " ";
    protected static final String TASK_SYMBOL_NA = " ";
    protected String taskStatus;
    protected String taskDescription;

    /**
     * Task constructor.
     *
     * @param taskDescription is the task description to be recorded.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = MARK_UNDONE;
    }

    /**
     * Return the task type.
     *
     * @return the task type.
     */
    public String getTaskSymbol() {
        return TASK_SYMBOL_NA;
    }

    /**
     * Return the task type.
     *
     * @return the task type.
     */
    public String getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * Return the task description.
     *
     * @return the task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Return the task timing.
     *
     * @return the task timing.
     */
    public String getTaskTiming() {
        return "";
    }

    /**
     * Return the output format for writing to hard disk.
     *
     * @return a string in correct file format for writing.
     */
    public String getFileFormat() {
        return getTaskSymbol() + " | " + getTaskStatus() + " | " + getTaskDescription();
    }

    /**
     * Mark the given task as done.
     */
    public void setTaskAsDone() {
        this.taskStatus = MARK_DONE;
    }

    /**
     * Return the true or false based on the completion status of the task.
     *
     * @return true if done, false if undone.
     */
    public boolean isDone() {
        return this.taskStatus.equals(MARK_DONE);
    }
}
