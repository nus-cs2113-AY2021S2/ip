package jarvis.task;

/**
 * Represents a todo task
 */
public class Todo extends Task {

    /**
     * Constructor for a todo object
     *
     * @param description description of a todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
