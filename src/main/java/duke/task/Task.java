package duke.task;

/**
 * Template for task sub-class
 */
public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor
     * @param taskName Task name
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public String getDescription(){
        return taskName;
    }

    /**
     * Marks this task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        char doneChar;
        if (isDone) {
            doneChar = 'X';
        } else {
            doneChar = ' ';
        }
        return String.format("[%c] %s", doneChar, taskName);
    }

    /**
     * Converts the task's data into String format
     * to be saved inside the hard disk.
     * @return Task's data in String format.
     */
    public abstract String getData();
}
