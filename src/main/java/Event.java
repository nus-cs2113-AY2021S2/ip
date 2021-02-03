public class Event extends Task{

    private static String timeOfEvent;

    public Event(String description, String timing){
        super(description);
        timeOfEvent = timing;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }
}
