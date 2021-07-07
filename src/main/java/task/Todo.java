package task;

/**
 * Todo class for handling the todo task type.
 */
public class Todo extends Task {
    private static final String TASK_SYMBOL_TODO = "T";

    /**
     * Todo constructor.
     *
     * @param taskDescription contains the task description of the task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Return the todo task type.
     *
     * @return the todo task type.
     */
    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_TODO;
    }
}
