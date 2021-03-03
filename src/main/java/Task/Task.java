package Task;

/**
 *  Gives the basic functionality of a task for inheritance
 */

public abstract class Task {
    protected static final String DATA_DELIMITER = " @_@ ";

    protected boolean isDone;
    protected String label;

    public Task(String label) {
        this.isDone = false;
        this.label = label;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getLabel() {
        return label;
    }

    public String getTime() {
        return " ";
    }

    /**
     * Converts a task to the file data format
     * @return File data formatted string
     */
    public String formatSaveTask() {
        String taskType = this.getClass().getSimpleName().toLowerCase();
        String isDone = this.isDone() ? "1" : "0";
        String label = this.getLabel();
        String time = this.getTime();

        return taskType + DATA_DELIMITER + isDone + DATA_DELIMITER + label + DATA_DELIMITER + time;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public void isDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns a checkbox string depending on whether task is done
     * @return A tick or a cross depending on the task completeness
     */
    public String getCheckbox() {
        return this.isDone() ? "[✔]":"[❌]";
    }

    /**
     * Returns the type of task,
     * @return A box that indicates the Task little
     */
    public String getType() {
        return "[ ]";
    }
}
