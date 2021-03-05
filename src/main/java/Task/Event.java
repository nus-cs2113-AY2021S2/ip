package Task;

/***
 * Represents a event with time in the task list.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTime() {
        return at;
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}