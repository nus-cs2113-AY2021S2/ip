public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
