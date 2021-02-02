public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    // Prints task
    public void printTask() {
        System.out.println("[D][" + getStatusIcon() + "] " + description + "(by:" + by +")");
    }
}
