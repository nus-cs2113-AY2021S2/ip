package duke.tasks;

/**
 * Todo class. 
 */
public class Todo extends Task {

    /**
     * Constructor for the <code>Todo</code> class. 
     * 
     * @param description description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T"; // T for Todo
    }

    /**
     * Returns the details of the task in printable format. 
     */
    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
