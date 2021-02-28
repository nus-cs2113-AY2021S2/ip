package ip.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task with description and a deadline (date or time)
 * status is undone unless marked done by the user
 */
public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    public String toDataString() {
        return "D" + super.toDataString() + " | " + by;
    }

    public String getDate() {
        return by;
    }
}
