package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task class with the necessary information
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task
     *
     * @return Tick if task is completed otherwise Cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the status of the task in proper formatting
     *
     * @return Proper format of the status
     */
    public String getStatus() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Returns description of the task
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns every information of the task in correct format
     *
     * @return status and description in correct format
     */
    public String toString() {
        return getStatus() + " " + getDescription();
    }

    /**
     * Returns every information of the task in correct format for storage into file
     *
     * @return status and description in correct format
     */
    public String toSaveFormat() {
        return description;
    }

    /**
     * Changes isDone variable to true to signify that the task is complete
     */
    public void taskComplete() {
        isDone = true;
    }


}

