public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", 'E', super.toString(), at);
    }
}
