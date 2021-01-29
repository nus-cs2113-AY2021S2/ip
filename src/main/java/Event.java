public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        setEventTime(eventTime);
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getEventTime() + ")";
    }
}
