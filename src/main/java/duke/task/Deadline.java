package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected boolean hasDate = false;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        LocalDate parsedDate = parseDate(by);
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



    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        if (hasDate) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
