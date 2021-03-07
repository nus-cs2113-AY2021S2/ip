package Duke.Task;

import Duke.Constant.Symbol;
import Duke.Constant.Number;

/**
 * Task can create the task objects.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of a task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status of a task.
     *
     * @return a tick if the task is done, and returns a cross if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? Symbol.DONE_SYMBOL : Symbol.NOT_DONE_SYMBOL);
    }

    /**
     * Sets the status status of a task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the type of a task.
     *
     * @return either "T", "D" or "E"
     */
    public String getType() {
        return "";
    }

    /**
     * Gets the integer type of a task.
     *
     * @return either 1 (done) or 0 (not done)
     */
    public String getIntegerType() {
        if (isDone) {
            return Number.DONE_STATUS;
        } else {
            return Number.NOT_DONE_STATUS;
        }
    }

    /**
     * Gets the string to be printed in the list.
     *
     * @return the details of a task
     */
    public String toString() {
        return "";
    }

    /**
     * Gets the appropriate printed format of a task
     * to be stored in Duke.txt
     *
     * @return the printed format of a task
     */
    public String toPrintedFormat() {
        return "";
    }

    /**
     * Gets the date of a task
     *
     * @return date
     */
    public String getDate() {
        return "";
    }
}