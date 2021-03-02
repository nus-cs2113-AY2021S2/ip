package duke.task;

/**
 * Represents a todo task in the task list
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() { return "T"; }
}
