package Duke.Tasks;

/**
 * Represents a Task in the TaskList.
 * String description represents the description of the task; and
 * boolean isDone represents whether the task has been completed.
 * The task class is an abstract class that holds the method to be used for the various task, Deadline, Todo, Event;
 * As such, abstract methods can be used as well like saveTask to obtain the format of the string to be saved or getDescription to
 * get the description of the task.
 */

public abstract class Task {
    protected String description;
    protected String by;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get a formatted message to save to file
     * @return a String representing the details of the task in the format to be saved
     */

    public abstract String saveTask();

    /**
     * Get a message about the details of the task
     * @return a String representing the description of the task in the format to be shown to user
     */
    public abstract String getDescription();

}