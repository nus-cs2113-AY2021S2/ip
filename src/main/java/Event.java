public class Event extends Task{

    private static String timeOfEvent;

    public Event(String description, String timing){
        super(description);
        timeOfEvent = timing;
        this.natureOfTask = "E";
    }

    public String getTimeOfEvent(){
        return this.timeOfEvent;
    }

    public String getSpecialDescription(){
        return this.getTimeOfEvent();
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeOfEvent + ")";
    }
}
