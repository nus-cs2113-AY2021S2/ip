public abstract class Task {
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
        return (this.isDone ? "X" : " ");
    }

    /**
     * Mark the current task instance as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the output String format to be used for saving the current task instance into the disk.
     *
     * @return a String representing the save file format of the current task instance.
     */
    public String getSaveFormatString() {
        return (this.isDone ? "1" : "0") + " | " + this.getDescription();
    }

    /**
     * Get a String representation of the current task instance, to be used for displaying
     * task information to the user.
     *
     * @return a String representation of the current task instance
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
