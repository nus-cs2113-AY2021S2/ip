package duke;

/**
 * Represents a Deadline subclass object that contains description, whether Task is done,
 * and also Deadline Time which is inputted with keyword "/by"
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}