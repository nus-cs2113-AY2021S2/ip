package ip.duke.task;

/**
 * Represents all types of tasks
 * parent class of Todo/Deadline/Event
 * includes description only
 * status is undone by default unless marked done by user
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a Task object with a given description and a default task status of undone.
     *
     * @param description the string given to describe this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    @Override
    /**
     * Returns the string format of the task to output the task when the program is running.
     *
     * @return the string format of this task with its status and description for output
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string format of the task to write the task into the file.
     *
     * @return the string format of this task with its status and description for data saving
     */
    public String toDataString() {
        return " | " + getStatusNum() + " | " + description;
    }

}
