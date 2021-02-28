package ip.duke.task;

/**
 * Represents a type of task with description and a startTime (date or time)
 * status is undone by default unless marked done by the user
 */
public class Event extends Task {
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String toDataString() {
        return "E" + super.toDataString() + " | " + at;
    }

    public String getDate() {
        return at;
    }
}
