package duke.task;

/*
Class Event for creating event tasks
*/
public class Event extends Task {

    protected String eventLocation;

    /*
    Constructor for Event Object
    Initialize description, taskType, eventLocation variables
    */
    public Event(String description, String eventLocation) {
        super(description, 'E');
        this.eventLocation = eventLocation;
    }

    /*
    Overrides toString() method from superclass
    Returns a string of task details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventLocation + ")";
    }

    /*
    Returns event location
     */
    public String getEventLocation(){
        return eventLocation;
    }
}