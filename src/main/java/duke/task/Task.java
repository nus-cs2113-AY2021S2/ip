package duke.task;

/**
 * Implements the Task class.
 *
 * @author Leonardo Irvin Pratama
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols, tick is \u2713
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Describes event to be saved in hard disk.
     *
     * @return String that will be stored on hard disk.
     */
    public String saveToHardDisk() {
        int isDone = this.isDone ? 1 : 0;
        return " | " + isDone + " | " + description;
    }
}
