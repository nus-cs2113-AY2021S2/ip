package duke.task;

/**
 * {@inheritDoc}
 * The difference between the Task class and the Todo class is that the Task class is abstract,
 * and thus cannot be implemented directly. In addition, the Todo class will automatically set its task type to 'T'.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo object with the specified description.
     *
     * @param description a description for the task to be added
     */
    public Todo(String description) {
        super(description);
        super.setTaskType('T');
    }
}
