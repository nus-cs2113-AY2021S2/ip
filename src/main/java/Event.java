public class Event extends Deadline{
    public Event(String description, String eventDuration) {
        super(description, eventDuration);
    }

    public String toString(){
        return "Event : " + super.toBaseString() + " || Happening on: " + this.getDateTime();
    }

}
