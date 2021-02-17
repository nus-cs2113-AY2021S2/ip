package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getDate() {
        return at;
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
