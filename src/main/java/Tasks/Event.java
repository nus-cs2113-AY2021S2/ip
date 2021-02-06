package Tasks;
public class Event extends Task {
    protected String at;

    public Event() {};

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getPrintedLine() {
        return "[E]" + super.getPrintedLine() + " (at: " + at + ")";
    }
}