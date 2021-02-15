package duke;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.separator = "/at";
    }

    public String getSeparatorType() {
        return "at";
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}