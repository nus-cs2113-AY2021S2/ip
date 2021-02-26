package duke.data.task;

public class Todo extends Task {
    private static final String PRINT_STRING_FORMAT = "[T]%s";
    private static final String FILE_OUTPUT_STRING_FORMAT = "%c %s";

    public Todo(String description) {
        this(description, false);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format(PRINT_STRING_FORMAT, super.toString());
    }

    @Override
    public String toFileEntry() {
        return String.format(FILE_OUTPUT_STRING_FORMAT, 'T', super.toFileEntry());
    }
}
