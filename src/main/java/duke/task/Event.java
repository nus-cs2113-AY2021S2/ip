package duke.task;

public class Event extends Task {
    protected String at;

    /**
     * Creates an Event object. 
     * 
     * @param description Name of event. 
     * @param at Date of event occurrence. 
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getTaskType() {
        return "event";
    }

    @Override
    public String getTaskDate() {
        return at;
    }

    /**
     * Returns the string format for file storing. 
     */
    @Override
    public String toStorageString() {
        return super.toStorageString() + ", " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}