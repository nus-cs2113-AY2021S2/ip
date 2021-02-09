public class Event extends Task{

    protected String eventPeriod;

    public static final String EVENT_LABEL = "[E]";

    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return EVENT_LABEL + super.toString() + "(at:" + eventPeriod + ")";
    }
}
