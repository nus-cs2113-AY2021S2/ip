public class Event extends Task{

    protected String at;

    public Event (String description, String at) {
        super(description+ "(at:" + at + ")");
        this.at = at;
        this.taskType = "[E]";
    }
}
