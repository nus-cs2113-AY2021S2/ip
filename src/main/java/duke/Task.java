package duke;

/**
 * Represents a Task superclass object that contains a description, and whether the Task is Done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + description);
    }
}