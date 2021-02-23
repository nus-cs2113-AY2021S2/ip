package duke.task;

import duke.Date;

public class Todo extends Task {
    /**
     * Creates a Todo object. 
     * 
     * @param description Name of todo task. 
     */
    public Todo(String description) {
        super(description);
    }

    public String getTaskType() {
        return "todo";
    }

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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}