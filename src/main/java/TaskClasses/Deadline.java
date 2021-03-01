package TaskClasses;

/**
 * Class that describes a deadline.
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for the deadline class if isDone, description and by parameters are passed in.
     * @param isDone the status of the deadline on whether it is marked as done.
     * @param description the description of the deadline.
     * @param by when the deadline must be done by
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Constructor for the deadline class if description and by parameters are passed in.
     * @param description the description of the deadline.
     * @param by when the deadline must be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the conversion of the deadline into String format.
     * @return the String format of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Overrides the conversion of the deadline to the save format.
     * @return the String that adhered to the save format of deadline.
     */
    @Override
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + by;
    }
}
