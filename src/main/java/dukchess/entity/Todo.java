package dukchess.entity;

/**
 * A Todo task
 */
public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public Todo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
