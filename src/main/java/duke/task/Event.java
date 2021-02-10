package duke.task;

public class Event extends Task {
    String eventTime;
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(at: " + eventTime + ")";
    }
}
