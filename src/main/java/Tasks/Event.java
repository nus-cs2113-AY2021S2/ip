package Tasks;
public class Event extends Task {
    protected String at;
    protected String type = "E";

    public Event() {};

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public String getType() {
        return type;
    }

    public String getPrintedLine() {
        return "[E]" + super.getPrintedLine() + " (at: " + at + ")";
    }
}