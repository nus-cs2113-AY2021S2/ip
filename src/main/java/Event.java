public class Event extends Task {
    private static final String EVENT_SYMBOL = "E";
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Get the current task instance's output String format, to be used for saving it into the disk.
     * This method overrides the getSaveFormatString() implementation in Task class.
     *
     * @return a String representing the save file format of the current task instance.
     */
    @Override
    public String getSaveFormatString() {
        return EVENT_SYMBOL + FILE_DELIMITER_WITH_SPACE + super.getSaveFormatString() +
                FILE_DELIMITER_WITH_SPACE + this.at;
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
        return String.format("[%s]%s (at: %s)", EVENT_SYMBOL, super.toString(), this.at);
    }
}
