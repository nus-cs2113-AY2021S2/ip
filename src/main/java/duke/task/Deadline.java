package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected boolean hasDate = false;

    /**
     * Constructor for Deadline Class
     *
     * @param description String description of task
     * @param by due date or time of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        LocalDate parsedDate = parseDate(by);
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
     * Returns the due date of the task
     *
     * @return String of by parameter
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a String representation of the task including the status icon, description and due date
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        if (hasDate) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }


}
