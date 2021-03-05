public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the current task instance's output String format, to be used for saving it into the disk.
     * This method overrides the getSaveFormatString() implementation in Task class.
     *
     * @return a String representing the save file format of the current task instance.
     */
    @Override
    public String getSaveFormatString() {
        return "D | " + super.getSaveFormatString() + " | " + this.by;
    }

    /**
     * Get a String representation of the current task instance, to be used for displaying
     * task information of the current task instance to the user. This method overrides the
     * toString() implementation in Task class.
     *
     * @return a String representation of the current task instance.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
