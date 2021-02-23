package Duke.Task;

public class TodoTask extends Task {

    private static final String COMMAND_TODO_WORD = "todo";

    public TodoTask(String taskDetail) {
        super(taskDetail);
    }

    @Override
    public String getTaskType() {
        return COMMAND_TODO_WORD;
    }

    @Override
    public String toString() {
        return "["+COMMAND_TODO_WORD+"]" + super.toString();
    }

    @Override
    public void markAsDone() {
        super.isDone = true;
    }
}
