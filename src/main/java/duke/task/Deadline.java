package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of Task that is used to represent Deadline tasks created by user
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor method for Deadline class
     *
     * @param description Description of the deadline
     * @param by Date and time (deadline) that the task needs to be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        String byString = by.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        return byString;
    }

    public LocalDateTime getByForSaving() {
        return by;
    }

    /**
     * Returns the status icon depending on if the deadline is completed
     *
     * @return A tick if the deadline is done, and a space (rather than a cross) if it hasn't yet
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Returns a String that contains all details of the deadline (status, description, and deadline)
     *
     * @return String containing all details of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
