package duke.data.task;

public class Task {
    private static final String PRINT_STRING_FORMAT = "[%s] %s";
    private static final String FILE_OUTPUT_STRING_FORMAT = "| %d | %s";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format(PRINT_STRING_FORMAT, getStatusIcon(), description);
    }

    public String toFileEntry() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, isDone ? 1 : 0, description);
    }
}
