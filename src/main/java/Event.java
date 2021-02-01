public class Event extends Task{
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + eventDate + ")";
    }
}
