package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String strAddToTxt() { return "E" + super.strAddToTxt() + " | " + at; }
}
