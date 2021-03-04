package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline in the task list.
 * Guarantees: Has a by/ field indicating the time argument
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try{
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException dtpe) {
            date = LocalDate.now();
            this.by = date.toString();
            System.out.println("Warning: Date time formatting incorrect! Changed to default date.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toText(int number) {
        return "D|" + number + "|" + description + "|" + by;
    }
}