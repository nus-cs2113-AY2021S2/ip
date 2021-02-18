package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected String type = "D";
    protected LocalDateTime datetime;

    public Deadline() {};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.datetime = LocalDateTime.parse(by, formatter);
    }

    public String getBy() {
        return by;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    public String getPrintedLine() {
        return "[D]" + super.getPrintedLine() + " (by: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}