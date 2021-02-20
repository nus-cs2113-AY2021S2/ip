package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected String byDate;
    protected LocalDateTime dateTime;

    public Deadline() {}

    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(by, dateTimeFormat);
    }

    public String getByDate() {
        return byDate;
    }

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public String printDescription() {
        return "[D]" + super.printDescription() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}