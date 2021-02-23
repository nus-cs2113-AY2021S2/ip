package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.dateTime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a")) + ")";
    }

    @Override
    public String toFileOutput() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
