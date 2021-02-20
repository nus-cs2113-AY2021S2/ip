package duke.data;

public class Todo implements Task {
    private final String description;
    private final boolean isDone;
    private final String message;

    /**
     * Constructs a Todo task.
     * @param description Name of task.
     * @param isDone True if this task is completed.
     */
    public Todo(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
        this.message = String.format("[T][%s] %s",
            isDone ? "X" : " ",
            description
        );
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
