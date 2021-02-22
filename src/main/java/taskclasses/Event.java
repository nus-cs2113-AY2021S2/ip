package taskclasses;

public class Event extends Task {
    protected String date;
    protected String time;

    public Event(String taskName, String date, String time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + getDate() + " " + getTime() + ")");
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public String getTime() {
        return this.time;
    }

    public String getDate() {
        return this.date;
    }

}
