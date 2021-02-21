package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected boolean hasDate = false;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        LocalDate parsedDate = parseDate(at);
        if (parsedDate != null) {
            hasDate = true;
            this.date = parsedDate;
        }
    }

    public LocalDate parseDate(String line) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(line);
        } catch (DateTimeParseException e) {
            //System.out.println("invalid date format");
        }
        return parsedDate;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        if (hasDate) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
