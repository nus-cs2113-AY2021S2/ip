package duke.task;

/**
 * Children object of Task class
 */
public class Todo extends Task{

    /**
     * Create Todo object
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return task with status icon
     *
     * @return task with status icon
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
