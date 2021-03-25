/**
 * Child class Deadline inherited from parent class Task
 */
public class Deadline extends Task{
    public String by;

    /**
     * Constructor of the class
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    /**
     * Change the description to a proper format
     */
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

}
