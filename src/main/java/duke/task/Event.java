package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected boolean hasDate = false;

    /**
     * Constructor for Event Class
     *
     * @param description String description of task
     * @param at date which task takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        LocalDate parsedDate = parseDate(at);
        if (parsedDate != null) {
            hasDate = true;
            this.date = parsedDate;
        }
    }

    /**
     * Returns a LocalDate object parsed from input String
     *
     * @param line String of date
     * @return LocalDate object parsed
     */
    public LocalDate parseDate(String line) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(line);
        } catch (DateTimeParseException e) {
            //System.out.println("invalid date format");
        }
        return parsedDate;
    }

    /**
     * Returns the date which task takes place
     *
     * @return String of at parameter
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns a String representation of the task including the status icon, description and date which it happens
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        if (hasDate) {
            return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
