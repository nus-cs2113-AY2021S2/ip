package Classes;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

    public String formatString() {
        int done = (isDone ? 1 : 0);
        return "D-" + done + "-" + description + "-" + by + "\n";
    }
}
