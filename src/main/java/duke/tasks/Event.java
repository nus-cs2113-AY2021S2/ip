package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class. 
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructor for the <code>Event</code> class. 
     * 
     * @param description description of the event
     * @param date date of the event
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "E"; // E for Event
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

    /**
     * Returns the details of the task in printable format. 
     */
    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
