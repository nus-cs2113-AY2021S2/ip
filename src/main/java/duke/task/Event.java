package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        String byString = at.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        return byString;
    }

    public LocalDateTime getAtForSaving() {
        return at;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }
}
