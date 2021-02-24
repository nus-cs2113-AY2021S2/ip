package Task;

public class Event extends Task{
    protected String startTime;

    public Event(String label, String startTime) {
        super(label);
        this.startTime = startTime;
    }

    @Override
    public String getTime() {
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
