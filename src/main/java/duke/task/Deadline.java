package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
