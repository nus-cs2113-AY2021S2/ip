public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.print("Got it. I've added this task:\n" + this + '\n');
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
