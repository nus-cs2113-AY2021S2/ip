public class Event extends Task {
    protected String eventTime;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.eventTime = at;
        this.taskType = "[E]";
    }

    @Override
    public String toString(){
        return super.toString() + "(at: " + eventTime + ")";
    }
}
