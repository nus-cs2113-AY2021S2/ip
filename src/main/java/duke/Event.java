package duke;

public class Event extends Task{
    protected String atDate;

    public Event(String description, String at) {
        super(description);
        this.atDate = at;
        this.separator = "/at";
    }

    public String getSeparatorType() {
        return "at";
    }

    public String getAt() {
        return this.atDate.toString();
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate + ")";
    }
}