package task;

/**
 * Represents a Event that contains a description, and the date or time it is conducted at.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date or time the Event is conducted at.
     *
     * @return The date or time the Event is conducted at.
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
