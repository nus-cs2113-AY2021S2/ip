package task;

/**
 * Represents a todo in the task list.
 */
public class Todo extends Task{

    /**
     * Create todo.
     * @param task String description of the todo.
     */
    public Todo(String task){
        super(task);
    }

    /**
     * Add [T] symbol that represents a todo.
     * @return String formatted task in text format with task type,
     * done status and task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}