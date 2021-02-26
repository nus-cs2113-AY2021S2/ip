package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    protected boolean isDone;

    /**
     * @param description is the description of the todo task.
     * Constructor of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return the todo description and status.
     */
    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }

}
