/**
 * Child class Event inherited from parent class Task
 */
public class Event extends Task{
    public String at;

    /**
     * Constructor of the main class
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    /**
     * Change the description to a proper format
     */
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
