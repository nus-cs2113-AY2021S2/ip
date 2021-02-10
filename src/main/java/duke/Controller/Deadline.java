package duke.Controller;

public class Deadline extends Task {
    protected String byDate;

    public Deadline() {};

    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
    }

    public String printDescription() {
        return "[D]" + super.printDescription() + " (by: " + byDate + ")";
    }
}