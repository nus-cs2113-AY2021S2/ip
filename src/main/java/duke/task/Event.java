package duke.task;

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
        return " (at: " + eventTime + ")";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "event " + getName() + " /at " + eventTime;
    }
}