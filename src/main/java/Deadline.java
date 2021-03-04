public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline Class
     *
     * @param description String description of task
     * @param by due date or time of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the task including the status icon, description and due date
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + by + ")";
    }

    /**
     * Returns a String representation of the status icon
     *
     * @return String representation of status icon
     */
    @Override
    public String getStatusIcon() {
        return "[D]"+ super.getStatusIcon();
    }

    public String getBy() {
        return by;
    }
}
