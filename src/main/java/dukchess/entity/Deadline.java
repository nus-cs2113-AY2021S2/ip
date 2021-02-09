package dukchess.entity;

public class Deadline extends Task {
    private String by;

    /**
     * Create a deadline using its description and its due date.
     * @param taskDescription
     * @param by
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by);
    }
}
