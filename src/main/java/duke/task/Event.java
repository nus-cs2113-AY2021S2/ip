package duke.task;

public class Event extends Task {
    protected static String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String getDate() {
        return "(at: " + this.eventTime + ")";
    }

    @Override
    public String getTime() {
        return this.eventTime;
    }
}
