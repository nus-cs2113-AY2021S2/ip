package duke.task;

/**
 * Represents a type of Task, with only description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String representation of a Todo task.
     * [T] represents Todo, [X] represents the task is done.
     * @return [T][X/ ] task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}