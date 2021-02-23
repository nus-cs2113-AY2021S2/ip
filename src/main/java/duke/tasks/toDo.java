package duke.tasks;

/**
 * Represents a todo class. toDo inherits task, in which the user can input and indicated by todo TASKNAME.
 */
public class toDo extends Task {

    /**
     * Creates todo task to be added into the task list.
     * @param description
     */
    public toDo(String description) {
        super(description);
    }

    /**
     * Prints details of todo.
     * @return Task type T and description.
     */
    public String printDescription() {
        return "[T]" + super.printDescription();
    }
}