package Class;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + at + ")";
    }

    public String getType(){
        return "event";
    }

    public String getTime(){
        return " /at " + this.at;
    }
}
