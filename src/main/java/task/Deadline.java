package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveToFile() {
        String done = "1";
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return done + " deadline " + description + " /by " + by + "\n";
    }
}
