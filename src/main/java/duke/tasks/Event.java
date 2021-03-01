package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String printDate() {
        return " (at: " + getDate() + ")";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
