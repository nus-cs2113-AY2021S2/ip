package Duke.Tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }


    public String saveTask() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
