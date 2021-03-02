package tasks;

public class Event extends Task {
    protected String at;

    /**
     * Creates an Event class that is a subclass of Task class
     *
     * @param description description of the task
     * @param at time which the task takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns every information about the task in correct format for storage
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toSaveFormat() {
        if (isDone) {
            return "E " + "Y " + description + "/d" + at + "\n";
        }
        else {
            return "E " + "N " + description + "/d" + at + "\n";
        }
    }

    /**
     * Returns every information about the task in correct format for printing
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.getDescription() + " (at: " + at + ")";
    }
}
