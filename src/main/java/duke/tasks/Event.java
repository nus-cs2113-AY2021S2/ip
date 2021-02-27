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
        return date;
    }

    public String printDate() {
        return " (at: " + date + ")";
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    public String getDetails() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription() + printDate();
    }

}
