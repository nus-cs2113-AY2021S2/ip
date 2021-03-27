package dukchess.entity;

/**
 * Abstraction of an event
 */
public class Event extends Task {
    private final String timeOfEvent;

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

    /**
     * Create an event using its task description, its isDone status, and when it is at.
     * @param taskDescription
     * @param isDone
     * @param timeOfEvent
     */
    public Event(String taskDescription, boolean isDone, String timeOfEvent) {
        super(taskDescription, isDone);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timeOfEvent);
    }
}
