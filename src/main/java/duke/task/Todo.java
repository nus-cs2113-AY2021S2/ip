package duke.task;

/**
 * Represents the todo task. An Todo object corresponds to the task without a date. 
 */
public class Todo extends Task {
    /**
     * Creates a Todo object. 
     * 
     * @param description Name of todo task. 
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the type of task. In this case, it is todo. 
     */
    public String getTaskType() {
        return "todo";
    }

    /**
     * Returns null as date is absent from todo tasks. 
     */
    @Override
    public String getTaskDate(String dateFormat) {
        return null;
    }
 
    /**
     * Returns the string format for file storing. 
     */
    @Override
    public String toStorageString() {
        return super.toStorageString();
    }

    /**
     * Returns the formatted todo task for display. 
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}