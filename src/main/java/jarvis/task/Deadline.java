package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    protected  String by;   // yyyy-mm-dd format

    /**
     * Constructor for a deadline task
     *
     * @param description description of the deadline task.
     * @param by time and date details of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
