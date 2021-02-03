public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.print("Got it. I've added this task:\n" + this + '\n');
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
