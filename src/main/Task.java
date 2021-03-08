package main;

import java.io.Serializable;

/**
 * Task object used in the Duke Application.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task object.
     *
     * @param description string description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    /**
     * Gets done status icon of Task.
     *
     * @return Y if task is done, N if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
    }

    /**
     * Gets description of Task object.
     *
     * @return description of Task object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats Task object into standard used by Duke application.
     *
     * @return formatted string of Task data
     */
    public String toString(){
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Sets description of Task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets completion status of Task object.
     *
     * @return completion status of Task object
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets done status of Task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }
}

