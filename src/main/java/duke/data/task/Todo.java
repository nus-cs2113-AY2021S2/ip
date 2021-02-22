package duke.data.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", 'T', super.toString());
    }

    @Override
    public String toFileEntry() {
        return String.format("%c %s", 'T', super.toFileEntry());
    }
}
