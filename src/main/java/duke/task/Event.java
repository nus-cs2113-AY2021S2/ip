package duke.task;

/**
 * Event class - object corresponds to a Event task represented by name and time
 */
public class Event extends Task {

    protected String eventTime;

    public Event(String description, String by) {
        super(description);
        this.eventTime = by;
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getDate() {
        return " (At: " + eventTime + ")";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "event " + getName() + " at " + eventTime;
    }
}