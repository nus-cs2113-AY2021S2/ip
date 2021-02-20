package duke.data;

public class Deadline implements Task {
    private final String description;
    private final boolean isDone;
    private final String message;
    private final String deadline;

    /**
     * Constructs a Deadline task.
     * @param description Name of task.
     * @param isDone True if this task is completed.
     * @param deadline Date/time this task expires.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        String fullDescription = String.format("%s (by: %s)", description, deadline);
        this.description = description.trim();
        this.deadline = deadline;
        this.isDone = isDone;
        this.message = String.format(
            "[D][%s] %s",
            isDone ? "X" : " ",
            fullDescription
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

    public String getDeadline() {
        return deadline;
    }
}
