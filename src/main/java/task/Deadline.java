package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    /**
     * @return partial display message when deadline added
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * format to save deadline to save file
     * @return String to save deadline into save file
     */
    public String saveToFile() {
        String done = "1";
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return done + " deadline " + description + " /by " + by + "\n";
    }
}
