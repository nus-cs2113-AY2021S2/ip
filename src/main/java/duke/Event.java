package duke;

/**
 * Represents a Event subclass object that contains description, whether Task is done,
 * and also Event Time which is inputted with keyword "/at"
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
