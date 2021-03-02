package duke.task;

/**
 * Represent a todo task
 */
public class Todo extends Task {

    public static final String TODO_LABEL = "[T]";

    /**
     * Constructor for todo
     * @param description describes what the todo task is about
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Checks if Todo's description is misssing
     * @param input the part of the todo string where description should appear
     * @throws TaskDescriptionMissingException exception arises when todo's description is not found
     */
    public static void checkIfToDoDescriptionExists(String input) throws TaskDescriptionMissingException {
        if (input.isBlank()) {
            Task.decreaseTaskCount();
            throw new TaskDescriptionMissingException();
        }
    }

    /**
     * Override 'toString' method to print out a customised message when printing a todo object.
     * @return
     */
    @Override
    public String toString() {
        return TODO_LABEL + super.toString();
    }
}
