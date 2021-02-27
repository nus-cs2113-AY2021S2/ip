public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getDescription() + " (at: " + at + ")";
    }

    @Override
    public String toSaveFormat() { return (super.toSaveFormat() + " | " + at); }
}
