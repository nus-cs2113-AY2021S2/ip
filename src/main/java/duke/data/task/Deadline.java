package duke.data.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", 'D', super.toString(), by);
    }

    @Override
    public String toFileEntry() {
        return String.format("%c %s | %s", 'D', super.toFileEntry(), by);
    }
}
