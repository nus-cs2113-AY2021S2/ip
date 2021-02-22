package duke.task;

/**
 * Task that has to be done without any specified
 * deadline.
 */
public class Todo extends Task {

    /**
     * Constructor
     * @param taskName Name of task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getData() {
        return String.format("T;%d;%s", (isDone?1:0), taskName);
    }
}
