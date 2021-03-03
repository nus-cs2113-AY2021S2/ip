package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class. 
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor for the <code>Deadline</code> class. 
     * 
     * @param description description of the deadline task
     * @param date due date of the deadline task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "D"; // D for Deadline
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
	}

    public String printDate() {
        return " (by: " + getDate() + ")";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Returns the details of the task in printable format. 
     */
    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
