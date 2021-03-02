/**
 * Representation for an event
 */
public class Event extends Task{
    protected String at;

    /**
     * Instantiates an event
     *
     * @param description event description
     * @param at time the event is on.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a formatted event for user to view
     *
     * @return formatted event string
     */
    @Override
    public String toString() {
        return "[E]" + super.getDescription() + " (at: " + at + ")";
    }

    /**
     * Returns a formatted event for saving
     *
     * @return formatted event string
     */
    @Override
    public String toSaveFormat() { return (super.toSaveFormat() + " | " + at); }
}
