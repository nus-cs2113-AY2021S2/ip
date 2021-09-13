/**
 * Superclass for the other task types
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an instantiation of a task
     *
     * @param description description for a task
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a formatted string for a task
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        return ("[T][" + getStatusIcon() + "] " + description);
    }

    /**
     * Returns the description for a task
     *
     * @return task description
     */
    public String getDescription() {
        return ("[" + getStatusIcon() + "] " + description);
    }

    /**
     * Returns the completion status of a task
     *
     * @return Tick or Cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the completion status of a task from save
     *
     * @param isDone whether a task is done
     */
    public void setIsDone(String isDone) {
        this.isDone = isDone.equals("1");
    }

    /**
     * returns formatted description for saving
     *
     * @return description of task
     */
    public String toSaveFormat() {
        return (description);
    }
}
