package duke.tasks;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getDate() {
        return "(at: " + date + ")";
    }

}
