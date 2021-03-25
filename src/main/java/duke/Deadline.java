package duke;

import static duke.Constant.*;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return DEADLINE_TASK_TYPE + "|" + (isDone ? MARK_DONE : MARK_UNDONE)
                + "|" + this.description + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
