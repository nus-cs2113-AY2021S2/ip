package duke;

import static duke.Constant.*;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return TODO_TASK_TYPE + "|"
                + (isDone ? MARK_DONE : MARK_UNDONE) + "|" + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
