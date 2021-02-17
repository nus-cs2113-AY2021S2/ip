package TaskClasses;

public class Event extends Task{
    protected String at;

    public Event(Boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + at;
    }
}
