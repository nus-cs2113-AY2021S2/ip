package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", 'E', super.toString(), at);
    }

    @Override
    public String toFileEntry() {
        return String.format("%c %s | %s", 'E', super.toFileEntry(), at);
    }
}
