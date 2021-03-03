package TaskClasses;

/**
 * Class that describes a todo task.
 */
public class Todo extends Task{
    /**
     * Constructor for the todo class if isDone and description parameters are passed in.
     * @param isDone the status of the todo task on whether it is marked as done.
     * @param description the description of the todo task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Constructor for the todo class class if description parameter is passed in.
     * @param description the description of the deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the conversion of the todo task into String format.
     * @return the String format of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides the conversion of the todo task to the save format.
     * @return the String that adhered to the save format of todo task.
     */
    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }
}
