package duke;

/**
 * Represents a task that can be created and store in Duke.
 * A <code>Task</code> object corresponds to
 * a task represented by a task description and a task status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status and description of a task in a line.
     *
     * @return Task status and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Saves the status and description of a task into file.
     *
     * @return Task status and description.
     */
    public String saveTask() {
        return "|" + isDone + "|" + description;
    }
}
