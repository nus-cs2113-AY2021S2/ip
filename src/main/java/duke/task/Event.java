package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public LocalDateTime getDate() {
        return at;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
