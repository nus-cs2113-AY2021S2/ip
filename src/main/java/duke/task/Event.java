package duke.task;

/**
 * Subclass of Task that is used to represent Event tasks created by user
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor method for Event class
     *
     * @param description Description of the event
     * @param at Date and time that the event is at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    /**
     * Returns the status icon depending on if the event has been marked as done
     *
     * @return A tick if the event is done, and a space (rather than a cross) if it isn't
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Returns a String that contains all details of the event (status, description, and date & time)
     *
     * @return String containing all details of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
