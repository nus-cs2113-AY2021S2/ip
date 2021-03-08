public abstract class Task {
    private static final String IS_DONE_STATUS_ICON = "X";
    private static final String IS_NOT_DONE_STATUS_ICON = " ";
    private static final String IS_DONE_STATUS = "1";
    private static final String IS_NOT_DONE_STATUS = "0";
    public static final String FILE_DELIMITER_WITH_SPACE = " | ";

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? IS_DONE_STATUS_ICON : IS_NOT_DONE_STATUS_ICON);
    }

    /**
     * Mark the current task instance as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the current task instance's output String format, to be used for saving it into the disk.
     *
     * @return a String representing the save file format of the current task instance.
     */
    public String getSaveFormatString() {
        return (this.isDone ? IS_DONE_STATUS : IS_NOT_DONE_STATUS) + FILE_DELIMITER_WITH_SPACE + this.getDescription();
    }

    /**
     * Get a String representation of the current task instance, to be used for displaying
     * task information of the current task instance to the user.
     *
     * @return a String representation of the current task instance.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
