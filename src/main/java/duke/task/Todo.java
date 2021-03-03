package duke.task;

public class Todo extends Task {

    /**
     * Constructor for Todo Class.
     *
     * @param description String description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the task including the status icon and description.
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
