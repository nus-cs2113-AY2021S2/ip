package duke.task;

import duke.common.Messages;

/**
 * Abstract that represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task. Sets task to be incomplete by default.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Alternative constructor for Task. Takes in description and completion status.
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return completion status of task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @param done completion status to set
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * @return status icon based on the task's completion status
     */
    public String getStatusIcon() {
        return (isDone ? Messages.ICON_DONE : " ");
    }

    /**
     * @return string for command line output
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method for all subclass to implement for storing to text file.
     * @return string for file output
     */
    public abstract String toFileOutput();
}
