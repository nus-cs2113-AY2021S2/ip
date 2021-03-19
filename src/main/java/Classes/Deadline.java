package Classes;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * @return String containing description of Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

    /**
     * @return String of formatted data to be written into the text file
     */
    public String formatString() {
        int done = (isDone ? 1 : 0);
        return "D-" + done + "-" + description + "-" + by + "\n";
    }
}
