package duke.task;

/**
 * Represents a type of Task, with description and a deadline.
 */
public class Deadline extends Task {

    private String description;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns String representation of a Deadline task.
     * [D] represents Deadline, [X] represents the task is done.
     * @return [D][X/ ] task description (at: deadline)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
