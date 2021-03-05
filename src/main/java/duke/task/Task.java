package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task Class.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the current status icon of whether the task is done.
     *
     * @return Y if task is done and N if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    /**
     * Returns the description of the task.
     *
     * @return String description of the task
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the task including the status icon and description.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
