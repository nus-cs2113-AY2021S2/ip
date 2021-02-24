package duke.task;

public class Event extends Task{
    protected String date;

    public Event (String description, String date) {
        super(description);
        this.date = date;
        this.taskType = "[E]";
    }

    @Override
    public String getDate () {
        return date;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + date + ")";
    }
}
