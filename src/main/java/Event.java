public class Event extends Task {

    protected String event;

    public Event(String description, String startTime, String event) {
        super(description);
        this.event = event;
    }

    /*@Override
    public String toString() {
        return "[E]" + description + " (at: " + event + ")";
    }*/

    @Override
    public String getDescription() {
        return description + " (at: " + event + ")";
    }
}