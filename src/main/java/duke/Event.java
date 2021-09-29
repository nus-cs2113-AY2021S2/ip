package duke;

import static duke.Constant.*;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return EVENT_TASK_TYPE + "|"
                + (isDone ? MARK_DONE : MARK_UNDONE) + "|" + this.description + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
