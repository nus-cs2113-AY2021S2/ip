public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + dueDate + ")";
    }
}
