package main;

/**
 * Subclass of Task object, requires event date/time.
 *
 * @author Jeremy Teo
 * @version 0.2
 * @since 2021-02-28
 */
public class Event extends Task {
    private final String eventDateBy;

    /**
     * Event object constructor.
     *
     * @param task string description of task
     * @param eventDateBy string date/time of event
     */
    public Event(String task, String eventDateBy) {
        super(task);
        this.eventDateBy = eventDateBy;
    }

    /**
     * Get status of the Event Task.
     *
     * @return string of the the Event Task completion status
     */

    @Override
    public String getStatusIcon() {
        return (isDone ? "[E][\u2713]" : "[E][\u2718]"); //return tick or X symbols
    }

    /**
     * Formats the Event description
     *
     * @return formatted string of Event description
     */
    public String getDescription() {
        String deadlineDescription = String.format("%s (at: %s)", description, eventDateBy);
        return deadlineDescription;
    }

}