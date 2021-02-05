public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + " (at: " + by + ")";
    }
}
