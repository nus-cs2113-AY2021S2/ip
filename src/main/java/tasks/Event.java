package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event. A <code>Event</code> object corresponds to
 * a task represented by description and 'at' - indicating when the event is conducted.
 */
public class Event extends Task {
    LocalDate at = null;
    String atString = "";

    /**
     * Constructor for Event object if 'as' is of String type.
     * @param description Description of the event object
     * @param at When the event will be conducted
     */
    public Event(String description, String at) {
        super(description);
        this.atString = at;
    }

    /**
     * Constructor for Event object if 'as' is of LocalDate type.
     * @param description
     * @param at
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Override toString() function.
     * @return message shown when printing an event object
     */
    public String toString() {
        if (at != null) {
            atString = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return String.format("[E][" + getStatusIcon() + "] " + description + " (at: " + atString + ")");
    }
}
