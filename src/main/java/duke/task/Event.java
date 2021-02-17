package duke.task;

public class Event extends Task{

    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "[E]";
    }

    @Override
    public String getDate () {
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + "(at:" + at + ")";
    }
}
