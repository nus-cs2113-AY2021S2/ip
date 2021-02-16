package duke.task;

public abstract class Task {
    /**
     * The status of the task.
     */
    protected boolean isDone;
    /**
     * The content of the task.
     */
    protected String taskContent;
    /**
     * The type of the task.
     */
    protected TaskType taskType;

    /**
     * Initializes the task object with given parameter as content.
     *
     * @param taskContent The content of the task.
     */
    public Task(String taskContent) {
        this.isDone = false;
        this.taskContent = taskContent;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets a new status of the task.
     *
     * @param done the new status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the content of the task.
     *
     * @return the content of the task.
     */
    public String getTaskContent() {
        return taskContent;
    }

    /**
     * Sets a new content of the task.
     *
     * @param taskContent the new content of the task.
     */
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Gets a String of time limit of a Task object, if applicable.
     *
     * @return The time limit of a Task in String.
     */
    public abstract String getTimeLimitString();
}
