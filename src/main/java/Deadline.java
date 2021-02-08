public class Deadline extends Task {

    String by = "";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return String.format("[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")");
    }
}
