package duke;

public class Event extends Task{
    protected String at;

    public Event(String description, char taskType, String at){
        super(description);
        this.taskType = taskType;
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
