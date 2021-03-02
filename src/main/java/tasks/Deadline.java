package tasks;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline Class which is a subclass of the Task Class
     *
     * @param description description of the task
     * @param by deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns every information about the task in correct format for storage
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toSaveFormat() {
        if (isDone) {
            return "D " + "Y " + description + "/d" + by + "\n";
        }
        else {
            return "D " + "N " + description + "/d" + by + "\n";
        }
    }

    /**
     * Returns every information about the task in correct format for printing
     *
     * @return String that has all the information about the task
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatus() + " " + super.getDescription() + " (by: " + by + ")";
    }

}
