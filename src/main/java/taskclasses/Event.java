package taskclasses;

public class Event extends Task {
    protected String by;

    public Event(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + by + ")");
    }
}
