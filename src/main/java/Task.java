public class Task {
    private final String message;
    private final boolean isDone;
    private final int index;

    /**
     * Constructs a task.
     * Requires the task number, a message, indication if task is done.
     */
    public Task(int index, String message, boolean isDone) {
        this.message = message;
        this.isDone = isDone;
        this.index = index;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public int getIndex() {
        return index;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("%d. [%s] %s",
                getIndex(),
                isDone ? "X" : " ",
                getMessage()
        );
    }
}
