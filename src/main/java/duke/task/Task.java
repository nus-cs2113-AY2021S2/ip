package duke.task;

/**
 * Represents a task. A task object corresponds to a task represented by a description and status. 
 */
public abstract class Task {
    protected String description;
    /** Boolean to track if task is completed */
    protected boolean isDone;

    /**
     * Creates a Task object with the given description. 
     * 
     * @param description Task description. 
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the task description. 
     * 
     * @return Task description for the task. 
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Sets the task description. 
     * 
     * @param description Name of the task.  
     */
    public void setTaskDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of the task. 
     * 
     * @return True if the task is marked as done, flase otherwise. 
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Sets the status of the task to true. 
     */
    public void setTaskStatus() {
        isDone = true;
    }

    /**
     * Marks a task accordingly to storage data
     * If true, mark task as done, otherwise ignore. 
     * 
     * @param importedTask Task. 
     * @param isDone Task mark status. 
     * @return Modified task. May or may not be changed. 
     */
    public void setTaskStatus(String isDone) {
        if (isDone.equals("true")) {
            setTaskStatus();
        }
    }

    /**
     * Returns task date. 
     * 
     * @return Task date for deadline and event tasks, null for todo. 
     */
    public abstract String getTaskDate();

    /**
     * Returns the status icon depending on isDone boolean. 
     * If the task is done (i.e. isDone is true), "X" is returned. 
     * If the task is not done (i.e. isDone is false), " " is returned. 
     * 
     * @return Status icon. 
     */
    public String getTaskStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string format for file storing. 
     */
    public abstract String getTaskType();

    /**
     * Formats task information for storage (delimitered by commas). 
     * 
     * @return Formatted string containing task type, description, status and date. 
     *         Date is null for todo tasks. 
     */
    public String toStorageString() {
        return String.format("%s, %s, %s, %s", getTaskType(), String.valueOf(getTaskStatus()), getTaskDescription(), getTaskDate());
    }

    /**
     * Formats task for display. 
     * 
     * @return Formatted string containing task type, description, status and date (if any). 
     */
    public String toString() {
        return String.format("[%s] %s", getTaskStatusIcon(), getTaskDescription());
    }
}