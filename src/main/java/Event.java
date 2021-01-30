public class Event extends Task {

    String at = "";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return String.format("[E][ ]" + description + "(at: " + at + ")");
    }
}
