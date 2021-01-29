public class Event extends TaskList{
    protected String at;

    public Event(String description1, String at) {
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
