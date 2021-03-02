package duke.task;

public class Event extends Task {

    private String description;
    private String timeSlot;

    public Event(String description, String timeSlot) {
        super(description);
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeSlot + ")";
    }
}
