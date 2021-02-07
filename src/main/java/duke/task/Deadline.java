package duke.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline object. 
     * 
     * @param description Name of task. 
     * @param by Deadline of task to complete. 
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}