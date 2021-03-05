package task;

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
    @Override
    public String saveTask() {
        String done = String.valueOf(isDone);
        return done + " event " + description + " /by " + eventDate + "\n";
    }
}
