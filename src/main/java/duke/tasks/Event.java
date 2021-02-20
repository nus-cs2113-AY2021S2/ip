package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    protected String atDate;
    protected LocalDateTime dateTime;

    public Event() {};

    public Event(String description, String at) {
        super(description);
        this.atDate = at;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(at, dateTimeFormat);
    }

    public String getAtDate() {
        return atDate;
    }

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public String printDescription() {
        return "[E]" + super.printDescription() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}