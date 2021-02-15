package task;

public class Todo extends Task {
    private static final String TASK_SYMBOL_TODO = "T";

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_TODO;
    }
}
