package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        typeIcon = "E";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", at);
    }
}
