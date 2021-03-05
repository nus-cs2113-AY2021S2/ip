package duke.task;

/**
 * A parent class.
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return the status of the task, X if task is done and blank if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return blank or X symbols
    }

    /**
     * sets tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return the task status
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] ";
    }


}