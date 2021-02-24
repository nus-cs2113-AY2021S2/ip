package duke.items;


/**
 * Event class
 */
public class Event extends Task {
    private String at;

    /**
     * Event constructor
     *
     * @param description Description of the task
     * @param atInput input String of date
     */
    public Event(String description, String atInput) {
        super(description);
        this.at = atInput;
    }

    /**
     * Change the by attribute of Event
     *
     * @param atInput new String of date
     */
    public void setAt(String atInput) {
        this.at = atInput;
    }

    /**
     * Returns the at attribute of Event object
     *
     * @return this.at
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the Type of Event object
     *
     * @return String "E"
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Prints the Event object in a certain format
     */
    @Override
    public void print(){
        if (this.isDone) {
            System.out.println("[E][\u2713] " + description + " (at: " + at + ")" );
        } else {
            System.out.println("[E][\u2718] " + description + " (at: " + at + ")" );
        }
    }
}