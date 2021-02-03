public class Event extends Task {

    protected String eventDate;

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }
    @Override
    public void printTask() {
        System.out.println("[E][" + getStatusIcon() + "] " + description + "(at:" + eventDate +")");
    }
}
