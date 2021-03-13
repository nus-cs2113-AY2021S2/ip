package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline created by the user. A <code>Deadline</code> corresponds to
 * a task description, its completed status, and its due date.
 */
public class Deadline extends Task {
    
    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[DL]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String byString = this.by.format(formatter);
        return byString;
    }

    @Override
    public boolean isUrgent() {
        if (this.isDone()) {
            return false;
        }
        LocalDateTime weekDateTime = LocalDateTime.now().plusDays(7);
        return this.by.isBefore(weekDateTime);
    }
}
