package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event created by the user. An <code>Event</code> corresponds to
 * a task description, its completed status, and its due date.
 */
public class Event extends Task {
    
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[EV]" + super.toString() + " (at: " + this.getAt() + ")";
    }

    public String getAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String atString = this.at.format(formatter);
        return atString;
    }
}
