package duke.task;

<<<<<<< HEAD
/**
 * Subclass of Task that is used to represent Deadline tasks created by user
 */
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

>>>>>>> 6ac82591c9ac6bd152a793df7e203a11f55898b6
public class Deadline extends Task {
    protected LocalDateTime by;

<<<<<<< HEAD
    /**
     * Constructor method for Deadline class
     *
     * @param description Description of the deadline
     * @param by Date and time (deadline) that the task needs to be completed by
     */
    public Deadline(String description, String by) {
=======
    public Deadline(String description, LocalDateTime by) {
>>>>>>> 6ac82591c9ac6bd152a793df7e203a11f55898b6
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
