package Task;

/**
 *  Keeps track of a Task that has a start time
 */

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
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + startTime + ")";
    }
}
