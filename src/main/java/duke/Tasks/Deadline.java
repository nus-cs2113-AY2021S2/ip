package duke.Tasks;

public class Deadline extends Task {
    protected String byDate;

    public Deadline() {}

    public Deadline(String description, String by) {
        super(description);
        this.byDate = by;
    }

    public String getByDate() {
        return byDate;
    }

    public String printDescription() {
        return "[D]" + super.printDescription() + " (by: " + byDate + ")";
    }
}