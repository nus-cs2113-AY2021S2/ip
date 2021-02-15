package task;

public class Todo extends Task {
    private static final String TASK_SYMBOL_TODO = "T";

    public Todo(String taskDescrption) {
        super(taskDescrption);
    }

    @Override
    public String getTaskSymbol() {
        return TASK_SYMBOL_TODO;
    }
}
