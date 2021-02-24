package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDateTime datetime;
    protected String type = "E";

    public Event() {};

    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.datetime = LocalDateTime.parse(at, formatter);
    }

    public String getAt() {
        return at;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    public String getPrintedLine() {
        return "[E]" + super.getPrintedLine() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm a")) + ")";
    }
}