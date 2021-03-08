public class Todo extends Task {
    private static final String TODO_SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    /**
     * Get the current task instance's output String format, to be used for saving it into the disk.
     * This method overrides the getSaveFormatString() implementation in Task class.
     *
     * @return a String representing the save file format of the current task instance.
     */
    @Override
    public String getSaveFormatString() {
        return TODO_SYMBOL + FILE_DELIMITER_WITH_SPACE + super.getSaveFormatString();
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
        return String.format("[%s]%s", TODO_SYMBOL, super.toString());
    }
}
