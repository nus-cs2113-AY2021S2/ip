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

    public String getTaskType() {
        return "deadline";
    }

    /**
     * Returns the string format for file storing. 
     */
    @Override
    public String toStorageString() {
        return super.toStorageString() + ", " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}