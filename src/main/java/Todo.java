public class Todo implements Task {
    private final String description;
    private final boolean isDone;
    private final int index;
    private final String message;

    /**
     * Constructs a todo task.
     * Requires the task number, a task description, indication if task is done.
     */
    public Todo(int index, String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
        this.index = index;
        this.message = String.format("[T][%s] %s",
            isDone ? "X" : " ",
            description
        );
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", getIndex(), getMessage());
    }
}
