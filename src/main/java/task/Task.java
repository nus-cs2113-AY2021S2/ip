package task;

/**
 * Task is the building block for the application
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /***
     *
     * @param Description description for the task object
     */
    public Task(String Description) {
        this.description = Description;
        this.isDone = false;
    }

    /**
     * Accessor Method
     *
     * @return description of Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accessor Method
     *
     * @return boolean value signalling if the task has been done or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Accessor method
     *
     * @return status icon corresponding to if the task has been done or not
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718] "); //return tick or X symbols
    }

    /**
     * @return standard string return format
     */
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Setter Method
     *
     * @return mark task as done
     */
    public boolean setDone() {
        return isDone = true;
    }
}