public class Event extends Task {
    String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(at: " + at + ")";
    }
}
