public class Event extends Task {
    protected String dateTime;

    public Event (String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getDateTime() {
        return "(at: " + dateTime + ")";
    }

}
