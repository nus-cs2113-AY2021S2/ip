package duke.tasks;

/**
 * Represents a Task class. A task can be indicated as an event, deadline or todo.
 */
public class Task{
    /**
     * Description of task.
     */
    protected String description;
    /**
     * Boolean expression for whether task is done or not.
     */
    protected boolean isDone;

    /**
     * Creates a Task object. that represents task that the user can input. A task can be todo, event or deadline.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets task description.
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets task as done or not upon loading the file.
     * @param done Boolean isDone.
     */
    public void setAsDone(boolean done) {
        isDone = done;
    }

    /**
     * Marks task as done and returns the boolean isDone as true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the status icon depending on boolean isDone.
     * @return A tick if true, else blank.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u0020");
    }

    /**
     * Prints the task description.
     * @return Task description.
     */
    public String printDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as 1 or 0 in save file.
     * @return 1 if done, else 0.
     */
    public int getSaveDone() {
        return (isDone? 1:0);
    }
}