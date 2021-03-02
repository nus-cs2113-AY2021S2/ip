public class Event extends Task {

    protected String at;

    /**
     * Constructor for Event Class
     *
     * @param description String description of task
     * @param at date which task takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a String representation of the task including the status icon, description and date which it happens
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " + at + ")";
    }
    @Override
    public String getStatusIcon() {
        return "[E]"+ super.getStatusIcon();
    }

    /**
     * Returns the date which task takes place
     *
     * @return String of at parameter
     */
    public String getAt() {
        return at;
    }
}
