package duke.task;

/**
 * Subclass of Task that is used to represent ToDo tasks created by user
 */
public class ToDo extends Task {

    /**
     * Constructor method for ToDo class
     *
     * @param description Description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String that contains all details of the ToDo (description)
     *
     * @return String containing all details of the ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
