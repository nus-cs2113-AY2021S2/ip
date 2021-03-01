package TaskClasses;

/**
 * Class that describes a general task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the task class if only description is passed in.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Constructor for the task class if only description is passed in.
     * @param isDone  the status of the task on whether it is marked as done.
     * @param description the description of the task.
     */
    public Task(Boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Getter for the status icon. (Tick or cross)
     * @return the tick or cross symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Setter for the done status of the task.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Convert the task into String format.
     * @return the String format of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Convert the task to the save format.
     * @return the String that adhered to the save format of the task.
     */
    public String toSaveFormat() {
        return " | " + isDone + " | " + description;
    }
}