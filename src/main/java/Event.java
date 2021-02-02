public class Event extends Task{
    protected String startTime;

    public Event(String label, String startTime) {
        super(label);
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getTypeString() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + startTime + ")";
    }
}
