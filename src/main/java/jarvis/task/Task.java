package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a general task object
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of a task
     *
     * @return X for completed and space for not completed
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set the status of a task
     *
     * @param isDone if a task is completed or not
     */
    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the description of a task
     *
     * @return description of the task
     */
    public String getTaskDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + description;
    }
}
