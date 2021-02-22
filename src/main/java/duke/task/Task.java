package duke.task;

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

    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String description) {
        this.description = description;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setTaskStatus() {
        isDone = true;
    }

    public abstract String getTaskDate(String dateFormat);

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

    public String toStorageString() {
        return String.format("%s, %s, %s, %s", getTaskType(), String.valueOf(getTaskStatus()), getTaskDescription(), getTaskDate("yyyy-MM-dd"));
    }

    public String toString() {
        return String.format("[%s] %s", getTaskStatusIcon(), getTaskDescription());
    }
}