public class Deadline implements Task {
    private final String description;
    private final boolean isDone;
    private final int index;
    private final String message;
    private final String deadline;

    /**
     * Constructs a deadline task.
     * Requires the task number, a task description, indication if task is done, and the deadline.
     */
    public Deadline(int index, String description, boolean isDone, String deadline) {
        String fullDescription = String.format("%s (by: %s)", description, deadline);
        this.description = fullDescription;
        this.deadline = deadline;
        this.isDone = isDone;
        this.index = index;
        this.message = String.format("[D][%s] %s",
                isDone ? "X" : " ",
                fullDescription
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

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("%d.%s", getIndex(), getMessage());
    }
}
