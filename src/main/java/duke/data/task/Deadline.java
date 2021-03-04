package duke.data.task;

/**
 * Represents a Deadline in the task list.
 * Guarantees: Has a by/ field indicating the time argument
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toText(int number) {
        return "D|" + number + "|" + description + "|" + by;
    }
}