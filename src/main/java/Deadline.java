/**
 * Representation for a deadline
 */
public class Deadline extends Task{

    protected String by;

    /**
     * Instantiates a deadline
     *
     * @param description deadline description
     * @param by time the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted deadline for user to view
     *
     * @return formatted deadline string
     */
    @Override
    public String toString() {
        return "[D]" + super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Returns a formatted deadline for saving
     *
     * @return formatted deadline string
     */
    @Override
    public String toSaveFormat() { return (super.toSaveFormat() + " | " + by); }
}
