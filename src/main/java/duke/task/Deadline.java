package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

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

    @Override
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
