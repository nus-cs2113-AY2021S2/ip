package duke.tasks;

/**
 * Represents a todo task.
 */
public class toDo extends Task {

    /**
     * toDo constructer.
     * @param description
     */
    public toDo(String description) {
        super(description);
    }

    /**
     * Prints Task description.
     * @return Task type T and description.
     */
    public String printDescription() {
        return "[T]" + super.printDescription();
    }
}