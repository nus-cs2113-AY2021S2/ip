package duke.task;

/**
 * Represents an event task
 */
public class Event extends Task {

    protected String eventPeriod;

    public static final String EVENT_LABEL = "[E]";

    /**
     * Constructor for event class
     * @param description a string on what the event is about
     * @param eventPeriod a date period which represents the duration of the event task
     */
    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    /**
     * Gets event period
     * @return the event period in string
     */
    public String getEventPeriod() {
        return eventPeriod;
    }

    /**
     * Changes the string of event period
     * @param eventPeriod the new duration period of the event task
     */
    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    /**
     * Override 'toString' method to print out a customised message when printing a event object.
     * @return
     */
    @Override
    public String toString() {
        return EVENT_LABEL + super.toString() + "(at: " + eventPeriod + ")";
    }
}
