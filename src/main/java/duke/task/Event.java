package duke.task;

public class Event extends Task {

    protected String eventLocation;

    public Event(String description, String eventLocation) {
        super(description, 'E');
        this.eventLocation = eventLocation;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventLocation + ")";
    }

    public void setEventLocation(String eventLocation){
        this.eventLocation = eventLocation;
    }

    public String getEventLocation(){
        return eventLocation;
    }
}