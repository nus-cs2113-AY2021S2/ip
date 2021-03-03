package TaskClasses;

/**
 * Class that describes an event.
 */
public class Event extends Task{
    protected String at;

    /**
     * Constructor for the event class if isDone, description and by parameters are passed in.
     * @param isDone the status of the event on whether it is marked as done.
     * @param description the description of the event.
     * @param at when the event must be done by
     */
    public Event(Boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    /**
     * Constructor for the event class if description and by parameters are passed in.
     * @param description the description of the event.
     * @param at when the event must be done by
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the conversion of the event into String format.
     * @return the String format of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Overrides the conversion of the event to the save format.
     * @return the String that adhered to the save format of event.
     */
    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + at;
    }
}
