package Duke.Tasks;

/**
 * Represents a Deadline Task
 * description represents the type of task this class is
 * by represents the date or time task should be completed by
 */

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }


    @Override
    public String saveTask() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
