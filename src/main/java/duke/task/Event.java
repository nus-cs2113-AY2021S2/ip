package duke.task;

/**
 * Deals with event task which consists of date/time component.
 */
public class Event extends Task {
    /**
     * Date/time of event.
     */
    private String at;

    /**
     * Constructs new event task.
     *
     * @param description Description of the new event task.
     * @param at Date/time for the new event task.
     */
    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getAt() + ")";
    }
}
