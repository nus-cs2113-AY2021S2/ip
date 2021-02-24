package tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Override toString() function.
     * @return message shown when printing a Todo object
     */
    public String toString() {
        return String.format("[T][" + getStatusIcon() + "] " + description);
    }
}
