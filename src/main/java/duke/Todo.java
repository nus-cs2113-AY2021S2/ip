package duke;

/**
 * Represents a todo task that can be created and store in Duke.
 * A <code>Todo</code> object corresponds to
 * a todo task represented by a task description and a task status.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    /**
     * Returns the status and description of a todo task in a line.
     *
     * @return Todo task status and description.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Saves the status and description of a todo task into file.
     *
     * @return Todo task status and description.
     */
    public String saveTask() {
        return "T" + super.saveTask();
    }
}
