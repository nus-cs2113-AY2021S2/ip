package task;

public class Event extends Task {
    protected String at;

    /**
     * Event constructor method
     *
     * @param description description of event
     * @param at          where and when the event is going to happen
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Accessor Method
     *
     * @return where and when the event is at
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + at + ")";
    }
}
