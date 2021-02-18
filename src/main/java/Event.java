public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + at + ")";
    }
    @Override
    public String getStatusIcon() {
        return "[E]"+ super.getStatusIcon();
    }

    public String getAt() {
        return at;
    }
}
