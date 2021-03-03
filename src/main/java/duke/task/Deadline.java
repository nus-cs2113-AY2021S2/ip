package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task, with description and a deadline.
 */
public class Deadline extends Task {
    private String description;
//    private String by;
    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns String representation of a Deadline task.
     * [D] represents Deadline, [X] represents the task is done.
     * @return [D][X/ ] task description (at: deadline)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}
