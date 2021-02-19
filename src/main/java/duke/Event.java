package duke;

/**
 * Represents an event that can be created and store in Duke.
 * An <code>Event</code> object corresponds to
 * an event represented by an event description, an event status, and an event time.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    /**
     * Returns the status, description, and time of an event in a line.
     *
     * @return Event status, description and time.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Saves the status, description and time of an event into file.
     *
     * @return Event status, description and time.
     */
    public String saveTask() {
        return "E" + super.saveTask() + "|" + at;
    }
}
