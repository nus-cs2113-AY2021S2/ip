package duke;

/**
 * Represents a task without any specific deadline or event duration. A ToDo object contains the
 * task descriptions, state, and some basic manipulations of the information
 * in the task such as printing the task information.
 */
public class ToDo extends Task {

    /**
     * Constructor of the ToDo class.
     *
     * @param description The formatted textual description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Prints the information of the task
     */
    @Override
    public void printTask() {
        System.out.printf("[T][%s] %s\n",super.getStatusIcon(), super.getDescription().substring(5));
    }
}
