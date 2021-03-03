package dukchess.entity;

public class Event extends Task {
    private String timeOfEvent;

    /**
     * Create an event based on its task description and when it is at.
     *
     * @param taskDescription
     * @param timeOfEvent
     */
    public Event(String taskDescription, String timeOfEvent) {
        super(taskDescription);
        this.timeOfEvent = timeOfEvent;
    }

    public Event(String taskDescription, boolean isDone, String at) {
        super(taskDescription, isDone);
        this.timeOfEvent = at;
    }

    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timeOfEvent);
    }
}
