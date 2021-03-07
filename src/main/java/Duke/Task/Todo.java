package Duke.Task;

import Duke.Constant.TaskType;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getType() {
        return TaskType.TODO_TYPE;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toPrintedFormat() {
        return TaskType.TODO_TYPE + " | " + super.getIntegerType() + " | " + description;
    }
}