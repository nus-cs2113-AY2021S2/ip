package duke.task;

public class Event extends Task {
    protected String eventTime;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.eventTime = at;
        this.taskType = "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + eventTime + ")";
    }
    @Override
    public String taskToText() {
        return "E|" + super.completed + "|" + super.taskDescription + "|" + eventTime;
    }
}
