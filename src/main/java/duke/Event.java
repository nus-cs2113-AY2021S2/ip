package duke;

public class Event extends Task{
    protected String atDate;

    /**
     * Constructor for Event class
     *
     * @param description  the description of the event
     * @param at date which the event is on
     */
    public Event(String description, String at) {
        super(description);
        this.atDate = at;
        this.separator = "/at";
    }

    /**
     * Returns the date which the event is on, after converting it to a string (from LocalDate class)
     *
     * @return the string form of the event date
     */
    public String getAt() {
        return this.atDate.toString();
    }

    /**
     * Returns the string form which is event
     *
     * @return string description, event
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Returns the task in string form
     *
     * @return String form of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate + ")";
    }
}