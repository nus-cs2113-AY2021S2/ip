public class Task {
    /** Description of task */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus() {
        isDone = true;
    }

    /**
     * Returns the status icon depending on isDone boolean. 
     * If the task is done (i.e. isDone is true), "X" is returned. 
     * If the task is not done (i.e. isDone is false), " " is returned. 
     * 
     * @return Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}