package duke.task;

/**
 * Class that represents a todo task.
 */
public class Todo extends Task {
    /**
     * Default constructor for Todo.
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Alternative constructor for Todo. Create todo task from existing information.
     * @param description the description of the todo
     * @param isDone completion status of the todo
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * @return string for command line output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return string for file output
     */
    @Override
    public String toFileOutput() {
        return this.toString();
    }
}
