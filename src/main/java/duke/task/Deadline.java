package duke.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected String by;

    public static final String DEADLINE_LABEL = "[D]";

    /**
     * Constructor for Deadline class
     * @param description a string on what the deadline is about
     * @param by a date which represents the due date of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets 'by' String
     * @return the string 'by'
     */
    public String getBy() {
        return by;
    }

    /**
     * Changes the string of 'by'
     * @param by the new due date of the deadline task
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Checks if the description of deadline exists
     * @param input the substring of user's input that is supposed to contain deadline's description
     * @throws TaskDescriptionMissingException Exception error upon missing task description error
     */
    public void checkIfDeadlineDescriptionExists(String input) throws TaskDescriptionMissingException {
        if (input.isBlank()) {
            Task.decreaseTaskCount();
            throw new TaskDescriptionMissingException();
        }
    }

    /**
     * Override 'toString' method to print out a customised message when printing a deadline object.
     * @return
     */
    @Override
    public String toString() {
        return DEADLINE_LABEL + super.toString() + "(by: " + by + ")";
    }
}

