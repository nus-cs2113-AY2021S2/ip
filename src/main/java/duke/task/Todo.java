package duke.task;

/**
 * Represents a to-do task created by the user. A <code>Todo</code> corresponds to
 * a task description and its completed status.
 */
public class Todo extends Task {
    
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[TD]" + super.toString();
    }
}
