package Duke.Tasks;

/**
 * Represents an Event Task
 * description represents the type of task this class is
 * at represents the date or time task should be happening at
 */

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

    @Override
    public String getDescription() {
        return description;
    }
}
