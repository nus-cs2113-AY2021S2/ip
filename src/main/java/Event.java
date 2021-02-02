public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    // Prints task
    public void printTask() {
        System.out.println("[E][" + getStatusIcon() + "] " + description + "(at:" + at +")");
    }
}
