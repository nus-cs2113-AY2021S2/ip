package duke.task;

public class Todo extends Task {
    /**
     * Creates a Todo object. 
     * 
     * @param description Name of todo task. 
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}