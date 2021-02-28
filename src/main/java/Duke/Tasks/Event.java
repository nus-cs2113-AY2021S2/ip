package Duke.Tasks;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + at;
    }
}
