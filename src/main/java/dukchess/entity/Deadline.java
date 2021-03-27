package dukchess.entity;

/**
 * Abstraction of a deadline
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Create a deadline using its description and its due date.
     * @param taskDescription
     * @param by
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Create a deadline using its description, isDone status, and due date.
     * @param taskDescription
     * @param isDone
     * @param by
     */
    public Deadline(String taskDescription, boolean isDone, String by) {
        super(taskDescription, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by);
    }
}
