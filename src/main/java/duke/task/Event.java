package duke.task;

/**
 * Represents a type of Task, with description and a timeslot.
 */
public class Event extends Task {

    private String description;
    private String timeSlot;

    public Event(String description, String timeSlot) {
        super(description);
        this.timeSlot = timeSlot;
    }

    /**
     * Returns String representation of a Event task.
     * [E] represents Event, [X] represents the task is done.
     * @return [E][X/ ] task description (at: timeSlot)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timeSlot + ")";
    }
}
